package com.nasa.exercise.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Rover {
  public String id;
  public String name;

  public String getId() {
    return id;
  }
  public void setId(final String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(final String name) {
    this.name = name;
  }
}
