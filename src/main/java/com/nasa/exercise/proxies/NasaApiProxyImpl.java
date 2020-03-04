package com.nasa.exercise.proxies;

import com.nasa.exercise.config.NasaApiProxyConfig;
import com.nasa.exercise.dtos.Photo;
import com.nasa.exercise.dtos.Photos;
import com.nasa.exercise.dtos.Rover;
import com.nasa.exercise.dtos.Rovers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NasaApiProxyImpl implements NasaApiProxy {

    private static final Logger logger = LoggerFactory.getLogger(NasaApiProxy.class);

    private NasaApiProxyConfig config;
    private RestTemplate restTemplate;

    public NasaApiProxyImpl(NasaApiProxyConfig config,
                            RestTemplate restTemplate) {
        this.config = config;
        this.restTemplate = restTemplate;
    }

    @Override
    public Rovers getRovers() {

        logger.info("Send request to get rovers to Nasa");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(config.getApiUrl() + "/rovers")
                .queryParam(config.getParamApiKey(), config.getApiKey());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<Rovers> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Rovers.class);

        return response.getBody();
    }

    @Override
    public Photos getPhotos(final String name, final String date) {

        logger.info("Send request to get phones of {} of date {}", name, date);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(config.getApiUrl() + "/rovers/" + name + "/photos")
                .queryParam(config.getParamApiKey(), config.getApiKey())
                .queryParam(config.getParamEarthDate(), date);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<Photos> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Photos.class);

        return response.getBody();
    }

    @Override
    public byte[] getPhotoImage(final String url) {

        logger.info("Send request to get phone {}", url);

        return restTemplate.getForObject(url, byte[].class);

    }
}
