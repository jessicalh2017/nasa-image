package com.nasa.exercise.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Rovers {
  public List<Rover> rovers;

  public List<Rover> getRovers() {
    return rovers;
  }

  public void setRovers(final List<Rover> rovers) {
    this.rovers = rovers;
  }
}
