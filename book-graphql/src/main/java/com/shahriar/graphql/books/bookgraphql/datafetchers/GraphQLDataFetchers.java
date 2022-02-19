package com.shahriar.graphql.books.bookgraphql.datafetchers;

import java.util.Map;

import graphql.schema.DataFetcher;
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

    public DataFetcher<Map<String, String>> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookDao.getBookById(bookId);                                  
        };
    }

    public DataFetcher<Map<String, String>> getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
        	// DataFetchers are executed from top down. "Source" is the book fetched above.
        	Map<String,String> book = dataFetchingEnvironment.getSource();        	
            String authorId = book.get("authorId");
            return authorDao.getAuthor(authorId);        	
        };
    }       
}
