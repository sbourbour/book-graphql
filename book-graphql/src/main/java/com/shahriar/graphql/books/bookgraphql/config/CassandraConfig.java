package com.shahriar.graphql.books.bookgraphql.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.oss.driver.api.core.CqlSession;


@Configuration
@EnableCassandraRepositories(basePackages = "com.shahriar.graphql.books.bookgraphql.dao")
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${cassandra.host}")
	String host;
	
	@Value("${cassandra.keyspace}")
	String keyspace;
	
	@Value("${cassandra.port}")
	String port;
	
	@Value("${cassandra.datacenter}")
	String localDatacenter;
    
    @Override
    public String getContactPoints() {
        return host;
    }
    
    @Override
    public int getPort() {
    	return Integer.parseInt(port);
    }

    @Override
    public String getKeyspaceName() {
        return keyspace;
    }
        
    public String getLocalDataCenter() {
        return localDatacenter;
    }
    
    @Override
    @Bean(name="CassandraSession")
    public CqlSessionFactoryBean cassandraSession() {
      CqlSessionFactoryBean factory = new CqlSessionFactoryBean();      
      factory.setPort(getPort());
      factory.setKeyspaceName(keyspace);
      factory.setContactPoints(host);
      factory.setLocalDatacenter(localDatacenter);
      return factory;
    }
       
       
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification
                .createKeyspace(keyspace).ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true).withSimpleReplication();
        return Arrays.asList(specification);
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }    
}
