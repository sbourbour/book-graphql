package com.shahriar.graphql.books.bookgraphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shahriar.graphql.books.bookgraphql.dao.BookDao;

public class Query implements GraphQLQueryResolver {
    private BookDao bookDao;

    public Query(BookDao bookDao) {
        this.bookDao = bookDao;
    }
   
}
