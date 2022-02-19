package com.shahriar.graphql.books.bookgraphql.resolver;

import java.util.Map;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shahriar.graphql.books.bookgraphql.dao.AuthorDao;
import com.shahriar.graphql.books.bookgraphql.domain.Author;

public class AuthorResolver implements GraphQLResolver<Author> {
    private AuthorDao authorDao;

    public AuthorResolver(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public Map<String, String> getAuthor(String authorId) {    	
		return authorDao.getAuthor(authorId);	    	
    }
}
