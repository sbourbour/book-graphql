package com.shahriar.graphql.books.bookgraphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shahriar.graphql.books.bookgraphql.dao.AuthorDao;
import com.shahriar.graphql.books.bookgraphql.dao.BookDao;
import com.shahriar.graphql.books.bookgraphql.resolver.AuthorResolver;
import com.shahriar.graphql.books.bookgraphql.resolver.Mutation;
import com.shahriar.graphql.books.bookgraphql.resolver.BookResolver;
import com.shahriar.graphql.books.bookgraphql.resolver.Query;

@Configuration
public class BookGraphqlConfiguration {
    
    @Bean
    public BookResolver bookResolver(BookDao bookDao) {
        return new BookResolver(bookDao);
    }

    @Bean
    public AuthorResolver authorResolver(AuthorDao authorDao) {
        return new AuthorResolver(authorDao);
    }

    @Bean
    public Query query(BookDao bookDao) {
        return new Query(bookDao);
    }

    @Bean
    public Mutation mutation(BookDao bookDao) {
        return new Mutation(bookDao);
    }
}
