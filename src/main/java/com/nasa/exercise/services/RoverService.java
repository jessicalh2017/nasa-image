package com.nasa.exercise.services;

import com.nasa.exercise.dtos.Photos;
import com.nasa.exercise.dtos.Rovers;

public interface RoverService {
  Rovers getRovers();
  Photos getPhotos(final String name, final String earthDate);
}
