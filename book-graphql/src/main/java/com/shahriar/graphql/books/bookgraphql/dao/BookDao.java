package com.shahriar.graphql.books.bookgraphql.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.datastax.oss.driver.api.core.CqlSession;

@Repository
public class BookDao {
	
	@Autowired
    @Qualifier("CassandraSession")
	CqlSession cassandraSession;
	
	private final String SELECT_BOOK_BY_ID = "SELECT id, title, title2, authorId, isbn, price, currency, format "
			+ " FROM shahriar.book "			
			+ "WHERE id = %d"
			;
	private final String BOOK_ID_COLUMN = "bookId";
	private final String BOOK_TITLE_COLUMN = "title";
	private final String BOOK_TITLE2_COLUMN = "title2";
	private final String BOOK_AUTHOR_ID_COLUMN = "authorId";
	private final String BOOK_ISBN_COLUMN = "isbn";
	private final String BOOK_PRICE_COLUMN = "price";
	private final String BOOK_CURRENCY_COLUMN = "currency";
	private final String BOOK_FORMAT_COLUMN = "format";	

    public BookDao() {        
    }

    public Map<String, String> getBookById(String bookId) {
		
		String queryById = String.format(SELECT_BOOK_BY_ID, Integer.parseInt(bookId));
				
		com.datastax.oss.driver.api.core.cql.ResultSet resultSet = cassandraSession.execute(queryById);
		
		List<com.datastax.oss.driver.api.core.cql.Row> rows = resultSet.all();
		
		Map<String, String> book = new HashMap<>();
		book.put(BOOK_ID_COLUMN, bookId);		
		// id is the primary key. There will be only one row for this Id.
		for(com.datastax.oss.driver.api.core.cql.Row row : rows) {			
			book.put(BOOK_TITLE_COLUMN, row.getString(BOOK_TITLE_COLUMN));			
			book.put(BOOK_TITLE2_COLUMN, row.getString(BOOK_TITLE2_COLUMN));			
			book.put(BOOK_AUTHOR_ID_COLUMN, String.valueOf(row.getInt(BOOK_AUTHOR_ID_COLUMN)));
			book.put(BOOK_ISBN_COLUMN, row.getString(BOOK_ISBN_COLUMN));
			book.put(BOOK_PRICE_COLUMN, String.valueOf(row.getDouble(BOOK_PRICE_COLUMN)));
			book.put(BOOK_CURRENCY_COLUMN, row.getString(BOOK_CURRENCY_COLUMN));
			book.put(BOOK_FORMAT_COLUMN, row.getString(BOOK_FORMAT_COLUMN));			
		}
	
		return book;
	}     
}
