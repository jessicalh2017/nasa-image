package com.nasa.exercise.services;


import com.nasa.exercise.dtos.Photos;
import com.nasa.exercise.dtos.Rovers;
import com.nasa.exercise.proxies.NasaApiProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoverServiceImpl implements  RoverService{

  private NasaApiProxy nasaApiProxy;

  public RoverServiceImpl(NasaApiProxy nasaApiProxy) {
    this.nasaApiProxy = nasaApiProxy;
  }

  @Override
  public Rovers getRovers() {
    return nasaApiProxy.getRovers();
  }

  @Override
  public Photos getPhotos(final String name, final String earthDate) {
    return nasaApiProxy.getPhotos(name, earthDate);
  }

}
