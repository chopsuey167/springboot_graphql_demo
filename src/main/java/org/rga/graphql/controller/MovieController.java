package org.rga.graphql.controller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieController {

  private final GraphQL graphQL;

  @PostMapping
  public ResponseEntity<Object> movies(@RequestBody String query) {
    ExecutionResult execute = graphQL.execute(query);
    return new ResponseEntity<>(execute, HttpStatus.OK);
  }
}
