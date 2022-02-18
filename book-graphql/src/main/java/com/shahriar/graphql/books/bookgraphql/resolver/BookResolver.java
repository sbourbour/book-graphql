package com.shahriar.graphql.books.bookgraphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shahriar.graphql.books.bookgraphql.dao.BookDao;
import com.shahriar.graphql.books.bookgraphql.domain.Book;

import org.json.JSONException;
import org.json.JSONObject;

public class BookResolver implements GraphQLResolver<Book> {
    private BookDao bookDao;

    public BookResolver(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public JSONObject getBook(String bookId) {
        try {
			return bookDao.getBookById(bookId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}