package com.nasa.exercise.services;

import com.nasa.exercise.proxies.NasaApiProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PhotoServiceImpl implements PhotoService {
    private static final Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);

    private static final String IMAGE_REPO = "./tmp/";

    private NasaApiProxy nasaApiProxy;

    public PhotoServiceImpl(NasaApiProxy nasaApiProxy) {
        this.nasaApiProxy = nasaApiProxy;
    }

    @Override
    public Path getPhoto(String name, String id, String imgSrc) throws IOException, URISyntaxException {

        final String fileName = new StringBuilder(IMAGE_REPO).append(name).append("_").append(id).append(".jpg").toString();
        Path path = Paths.get(fileName);
        File image;
        if (Files.exists(Paths.get(fileName))) {
            // Look up the file in image repo
            image = path.toFile();
        } else {
            // Save the image from Nasa to repo
            byte[] bytes = nasaApiProxy.getPhotoImage(imgSrc);
            Path newFile = Files.createFile(path);
            Files.write(newFile, bytes);
            image = newFile.toFile();
        }

        return image.toPath();
    }
}
