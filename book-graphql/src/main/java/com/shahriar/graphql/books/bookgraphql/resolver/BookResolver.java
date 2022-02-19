package com.shahriar.graphql.books.bookgraphql.resolver;

import java.util.Map;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shahriar.graphql.books.bookgraphql.dao.BookDao;
import com.shahriar.graphql.books.bookgraphql.domain.Book;

public class BookResolver implements GraphQLResolver<Book> {
    private BookDao bookDao;

    public BookResolver(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Map<String, String> getBook(String bookId) {        
		return bookDao.getBookById(bookId);		
    }
}