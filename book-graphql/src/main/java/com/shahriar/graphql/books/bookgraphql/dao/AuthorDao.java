package com.shahriar.graphql.books.bookgraphql.dao;


import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.datastax.oss.driver.api.core.CqlSession;

@Repository
public class AuthorDao {
	
	@Autowired
    @Qualifier("CassandraSession")
	CqlSession cassandraSession;
	
	private final String SELECT_AUTHOR_BY_ID = "SELECT authorId " + 
			" , firstName " + 
			" , lastName " + 
			" , birthDate " + 
			" , deceasedDate " + 
			" , gender "
			+ " FROM shahriar.author "
			+ " WHERE authorId = %d";
	
	private final String AUTHOR_ID_COLUMN = "authorId";
	private final String AUTHOR_FIRST_NAME_COLUMN = "firstName";
	private final String AUTHOR_LAST_NAME_COLUMN = "lastName";
	private final String AUTHOR_BIRTH_DATE_COLUMN = "birthDate";
	private final String AUTHOR_DECEASED_DATE_COLUMN = "deceasedDate";
	private final String AUTHOR_GENDER_COLUMN = "gender";
  
    public AuthorDao() {        
    }
    
    public JSONObject getAuthor(String authorId) throws JSONException {
    	String queryById = String.format(SELECT_AUTHOR_BY_ID, Integer.parseInt(authorId));
    	System.out.println(queryById);
		
		com.datastax.oss.driver.api.core.cql.ResultSet resultSet = cassandraSession.execute(queryById);
		
		List<com.datastax.oss.driver.api.core.cql.Row> rows = resultSet.all();
		
		JSONObject author = new JSONObject();		
		author.put(AUTHOR_ID_COLUMN, authorId);		
		// id is the primary key. There will be only one row for this Id.
		for(com.datastax.oss.driver.api.core.cql.Row row : rows) {			
			author.put(AUTHOR_FIRST_NAME_COLUMN, row.getString(AUTHOR_FIRST_NAME_COLUMN));			
			author.put(AUTHOR_LAST_NAME_COLUMN, row.getString(AUTHOR_LAST_NAME_COLUMN));
			author.put(AUTHOR_BIRTH_DATE_COLUMN, row.getString(AUTHOR_BIRTH_DATE_COLUMN));
			author.put(AUTHOR_DECEASED_DATE_COLUMN, row.getString(AUTHOR_DECEASED_DATE_COLUMN));
			author.put(AUTHOR_GENDER_COLUMN, row.getString(AUTHOR_GENDER_COLUMN));					
		}
	
		return author;
    }    
}
