package com.shahriar.graphql.books.bookgraphql.resolver;

import org.json.JSONException;
import org.json.JSONObject;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shahriar.graphql.books.bookgraphql.dao.AuthorDao;
import com.shahriar.graphql.books.bookgraphql.domain.Author;

public class AuthorResolver implements GraphQLResolver<Author> {
    private AuthorDao authorDao;

    public AuthorResolver(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public JSONObject getAuthor(String authorId) {
    	try {
			return authorDao.getAuthor(authorId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
}
