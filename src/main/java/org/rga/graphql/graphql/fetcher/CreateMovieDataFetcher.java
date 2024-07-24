package org.rga.graphql.graphql.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.rga.graphql.model.Movie;
import org.rga.graphql.repository.MovieRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateMovieDataFetcher implements DataFetcher<Movie> {

  private final MovieRepository movieRepository;

  @Override
  public Movie get(DataFetchingEnvironment environment) throws Exception {

    return null;
  }
}
