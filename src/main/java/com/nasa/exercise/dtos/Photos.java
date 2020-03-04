package com.nasa.exercise.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Photos {
  public List<Photo> photos;

  public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(final List<Photo> photos) {
    this.photos = photos;
  }

}
