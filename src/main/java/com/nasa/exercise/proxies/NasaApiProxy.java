package com.nasa.exercise.proxies;

import com.nasa.exercise.dtos.Photos;
import com.nasa.exercise.dtos.Rover;
import com.nasa.exercise.dtos.Rovers;
import java.util.List;

public interface NasaApiProxy {

  Rovers getRovers();

  Photos getPhotos(final String name, final String date);

  byte[] getPhotoImage(final String url);
}
