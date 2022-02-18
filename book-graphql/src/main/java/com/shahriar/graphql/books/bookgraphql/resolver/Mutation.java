package com.shahriar.graphql.books.bookgraphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.shahriar.graphql.books.bookgraphql.dao.BookDao;

public class Mutation implements GraphQLMutationResolver {
    private BookDao bookDao;

    public Mutation(BookDao postDao) {
        this.bookDao = postDao;
    }    
}