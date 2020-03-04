package com.nasa.exercise.config;


import com.nasa.exercise.proxies.NasaApiProxy;
import com.nasa.exercise.proxies.NasaApiProxyImpl;
import com.nasa.exercise.services.PhotoService;
import com.nasa.exercise.services.PhotoServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
        NasaApiProxyConfig.class,
        NasaApiProxyImpl.class,
        PhotoService.class,
        PhotoServiceImpl.class

})
public class TestConfig {
}

