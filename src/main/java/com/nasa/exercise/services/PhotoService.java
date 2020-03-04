package com.nasa.exercise.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public interface PhotoService {
    Path getPhoto(String name, String id, String imgSrc) throws IOException, URISyntaxException;
}
