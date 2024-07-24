package org.rga.graphql.graphql.provider;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.rga.graphql.Movie;
import org.rga.graphql.MovieRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GraphQLProvider {

  private MovieRepository movieRepository;

  @PostConstruct
  public void init() {
    loadDataIntoHSQL();
  }

  private void loadDataIntoHSQL() {
    Stream.of(Movie.builder().id("1001").title("Guason")
            .actors(new String[]{"Joaquin Phoenix", "Robert De Niro"})
            .directors(new String[]{"Todd Phillips"}).releaseDate("3 de octubre de 2019")
            .build(),
        Movie.builder().id("1002").title("Avengers: Endgame")
            .actors(new String[]{"Robert Downey Jr.", "Scarlett Johansson", "Chris Evans"})
            .directors(new String[]{"Anthony Russo", "Joe Russo"}).releaseDate("22 de abril de 2019")
            .build()
    ).forEach(movie -> movieRepository.save(movie));
  }

}
