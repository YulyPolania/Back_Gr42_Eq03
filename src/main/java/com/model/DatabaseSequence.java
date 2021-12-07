package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Document(collection = "database_sequences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseSequence {

  @Id
  private String id;
  private Long seq;

}