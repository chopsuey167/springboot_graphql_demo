package org.rga.graphql.config;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.rga.graphql.graphql.fetcher.AllMoviesDataFetcher;
import org.rga.graphql.graphql.fetcher.CreateMovieDataFetcher;
import org.rga.graphql.graphql.fetcher.MovieByIdDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@AllArgsConstructor
public class GraphQLConfig {

  @Autowired
  private final AllMoviesDataFetcher allMoviesDataFetcher;
  @Autowired
  private final MovieByIdDataFetcher movieByIdDataFetcher;
  @Autowired
  private final CreateMovieDataFetcher createMovieDataFetcher;

  @Bean(name = "graphQLBean")
  public GraphQL graphQL(@Qualifier("runtimeWiring") RuntimeWiring runtimeWiring) throws IOException {
    SchemaParser schemaParser = new SchemaParser();
    TypeDefinitionRegistry typeDefinitionRegistry = new TypeDefinitionRegistry();
    String schemaFile = new ClassPathResource("graphql/schema.graphqls").getFile().getAbsolutePath();
    typeDefinitionRegistry.merge(schemaParser.parse(new File(schemaFile)));
    GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    return GraphQL.newGraphQL(graphQLSchema).build();
  }

  @Bean(name = "runtimeWiring")
  RuntimeWiring runtimeWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type("Query", typeWiring -> typeWiring
            .dataFetcher("allMovies", allMoviesDataFetcher)
            .dataFetcher("movieById", movieByIdDataFetcher))
        .type("Mutation", typeWiring -> typeWiring
            .dataFetcher("createMovie", createMovieDataFetcher)
        )
        .build();
  }
}
