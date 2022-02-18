package com.shahriar.graphql.books.bookgraphql.domain;

import lombok.Data;

@Data
public class Book {
	private Integer id;
    private String title;
    private String title2;
    private String isbn;
    private Double price;
    private String currency;
    private String authorId;
    
    public Book() {
    	super();
    }
    
	public String getAuthorId() {
		return authorId;
	}
}
