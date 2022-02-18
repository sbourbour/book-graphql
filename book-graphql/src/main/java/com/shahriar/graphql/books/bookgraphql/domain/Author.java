package com.shahriar.graphql.books.bookgraphql.domain;

import lombok.Data;

@Data
public class Author {
	private Integer authorId;
    private String firstName;
    private String lastName;
    private String birthDate;
    
    public Author() {
    	super();
    }   
}
