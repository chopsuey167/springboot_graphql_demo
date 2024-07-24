package org.rga.graphql.graphql.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.rga.graphql.Movie;
import org.rga.graphql.MovieRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieByIdDataFetcher implements DataFetcher<Movie> {

  private final MovieRepository movieRepository;

  @Override
  public Movie get(DataFetchingEnvironment environment) throws Exception {
    String id= environment.getArgument("id");
    return movieRepository.findById(id).orElse(null);
  }
}
