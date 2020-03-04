package com.nasa.exercise.tests;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.exercise.NasaImageApplication;
import com.nasa.exercise.config.NasaApiProxyConfig;
import com.nasa.exercise.config.TestConfig;
import com.nasa.exercise.dtos.Rover;
import com.nasa.exercise.dtos.Rovers;
import com.nasa.exercise.proxies.NasaApiProxy;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NasaImageApplication.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = TestConfig.class)
public class ConfigTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @Autowired
    private NasaApiProxyConfig nasaApiProxyConfig;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnCorrectSettings() {
        assertEquals(nasaApiProxyConfig.getApiKey(), "3qLHNmBgKXL7U9HMZMg82UxzCIZTDgd4ukh8LZYY");
        assertEquals(nasaApiProxyConfig.getApiUrl(), "https://api.nasa.gov/mars-photos/api/v1");
        assertEquals(nasaApiProxyConfig.getParamApiKey(), "api_key");
        assertEquals(nasaApiProxyConfig.getParamEarthDate(), "earth_date");
    }

}
