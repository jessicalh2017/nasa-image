package com.nasa.exercise.tests;

import com.nasa.exercise.NasaImageApplication;
import com.nasa.exercise.config.NasaApiProxyConfig;
import com.nasa.exercise.config.TestConfig;
import com.nasa.exercise.proxies.NasaApiProxy;
import com.nasa.exercise.services.PhotoService;
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

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NasaImageApplication.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = TestConfig.class)
public class PhotoServiceTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @Autowired
    private NasaApiProxyConfig nasaApiProxyConfig;

    @Autowired
    private PhotoService photoService;

    @MockBean
    private NasaApiProxy nasaApiProxy;

    @Before
    public void setUp() throws IOException, URISyntaxException {

        Path image = Paths.get(getClass().getResource("/mar_surface.jpg").toURI());
        byte[] bytes = Files.readAllBytes(image);
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Mockito.when(nasaApiProxy.getPhotoImage(any(String.class))).thenReturn(Files.readAllBytes(image));
    }

    @Test
    public void shouldSaveFileToRepo() throws IOException, URISyntaxException {
        Path path = photoService.getPhoto("Curiosity", "1", "url");
        assertTrue(path.toAbsolutePath().endsWith("Curiosity_1.jpg"));
    }

}
