package com.nasa.exercise.tests;

import com.nasa.exercise.NasaImageApplication;
import com.nasa.exercise.config.TestConfig;
import com.nasa.exercise.services.DateService;
import com.nasa.exercise.services.PhotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NasaImageApplication.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = TestConfig.class)
public class DateServiceTests {

    @Autowired
    private DateService dateService;

    @Test
    public void shouldContainValidDatesOnly() throws IOException, URISyntaxException {
        List<String> dates = dateService.getDates();
        assertTrue(dates.contains("2017-02-27"));
        assertTrue(dates.contains("2018-06-02"));
        assertTrue(dates.contains("2016-07-13"));
        assertFalse(dates.contains("2018-04-31"));
        assertFalse(dates.contains("2018-05-31"));
        assertFalse(dates.contains("June 2, 2018"));
        assertTrue(dates.size() == 3);
    }

}
