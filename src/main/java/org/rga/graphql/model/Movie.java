package org.rga.graphql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

  @Id
  private String id;
  private String title;
  private String[] directors;
  private String[] actors;
  private String releaseDate;
}
