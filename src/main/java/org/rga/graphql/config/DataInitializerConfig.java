package org.rga.graphql.config;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.rga.graphql.model.Movie;
import org.rga.graphql.repository.MovieRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializerConfig {

  private MovieRepository movieRepository;

  @PostConstruct
  public void init() throws IOException {
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
