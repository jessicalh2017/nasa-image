package com.nasa.exercise.controllers;

import com.nasa.exercise.dtos.Photo;
import com.nasa.exercise.dtos.Photos;
import com.nasa.exercise.dtos.Rovers;
import com.nasa.exercise.proxies.NasaApiProxy;
import com.nasa.exercise.services.PhotoService;
import com.nasa.exercise.services.RoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("mynasa/rovers")
public class RoverController {

  private static final Logger logger = LoggerFactory.getLogger(RoverController.class);
  private RoverService roverService;
  private PhotoService photoService;

  public RoverController(RoverService roverService, PhotoService photoService) {
    this.roverService = roverService;
    this.photoService = photoService;
  }

  @GetMapping
  public Rovers getRovers() {
    return roverService.getRovers();
  }

  @GetMapping("{name}/photos")
  public Photos getPhotos(
      @PathVariable(value = "name") String name,
      @RequestParam(value = "earthDate") String earthDate) {

    return roverService.getPhotos(name, earthDate);
  }

  @GetMapping("{name}/photos/{id}/image")
  public ResponseEntity<byte[]> getImage(
          @PathVariable(value = "name") String name,
          @PathVariable(value = "id")  String id,
          @RequestParam("img_src") String imgSrc) throws IOException, URISyntaxException {
    Path imagePath = photoService.getPhoto(name, id, imgSrc);
    return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(Files.readAllBytes(imagePath));
  }
}
