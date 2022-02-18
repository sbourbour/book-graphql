package com.shahriar.graphql.books.bookgraphql.controller;

import graphql.ExecutionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shahriar.graphql.books.bookgraphql.service.GraphQLService;

@RequestMapping("/graphql/books")
@RestController
public class BookController {
	
    private static Logger logger = LoggerFactory.getLogger(BookController.class);
    private GraphQLService graphQLService;
    
    @Autowired
    public BookController(GraphQLService graphQLService) {
        this.graphQLService=graphQLService;
    }
    
    @PostMapping
    public ResponseEntity<Object> queryGraphQL(@RequestBody String query){
        
        ExecutionResult result = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}