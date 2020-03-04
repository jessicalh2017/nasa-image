package com.nasa.exercise.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.exercise.NasaImageApplication;
import com.nasa.exercise.config.TestConfig;
import com.nasa.exercise.dtos.Photo;
import com.nasa.exercise.dtos.Photos;
import com.nasa.exercise.dtos.Rover;
import com.nasa.exercise.dtos.Rovers;
import com.nasa.exercise.proxies.NasaApiProxy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NasaImageApplication.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = TestConfig.class)
public class ControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldGetRovers() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(get("/mynasa/rovers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Rovers.class)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetPhotos() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(get("/mynasa/rovers/Curiosity/photos?earthDate=2018-02-07")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(Photos.class)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetDates() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(get("/mynasa/dates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(List.class)))
                .andExpect(status().isOk());
    }
}
