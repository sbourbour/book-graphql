package com.shahriar.graphql.books.bookgraphql.datafetchers;

import java.util.Map;

import com.google.gson.Gson;
import graphql.schema.DataFetcher;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.shahriar.graphql.books.bookgraphql.dao.AuthorDao;
import com.shahriar.graphql.books.bookgraphql.dao.BookDao;


@Component
public class GraphQLDataFetchers {
  
    private BookDao bookDao;
    private AuthorDao authorDao;
    
    public GraphQLDataFetchers(BookDao bookDao, AuthorDao authorDao) {
    	this.bookDao = bookDao;
    	this.authorDao = authorDao;
    }

    public DataFetcher<Map> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            JSONObject book = bookDao.getBookById(bookId);            
            if(book != null) {
            	Gson gson = new Gson();
            	Map bookAsMap = gson.fromJson(book.toString(), Map.class);
            	
            	return bookAsMap;
            }            
            return null;            
        };
    }

    public DataFetcher<Map> getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
        	// DataFetchers are executed from top down. "Source" is the book fetched above.
        	Map<String,String> book = dataFetchingEnvironment.getSource();
        	System.out.print(book.toString());
            String authorId = book.get("authorId");
            JSONObject author = authorDao.getAuthor(authorId);
        	if(author != null) {        		
	        	Gson gson = new Gson();
	        	Map authorAsMap = gson.fromJson(author.toString(), Map.class);
	        	
	        	return authorAsMap;
        	}
        	return null;
        };
    }       
}
