package org.rga.graphql.graphql.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import lombok.AllArgsConstructor;
import org.rga.graphql.Movie;
import org.rga.graphql.MovieRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AllMoviesDataFetcher implements DataFetcher<List<Movie>> {

  private final MovieRepository movieRepository;

  @Override
  public List<Movie> get(DataFetchingEnvironment environment) {
    return movieRepository.findAll();
  }
}
