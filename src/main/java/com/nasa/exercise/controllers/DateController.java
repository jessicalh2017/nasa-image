package com.nasa.exercise.controllers;

import com.nasa.exercise.dtos.Rovers;
import com.nasa.exercise.services.DateService;
import com.nasa.exercise.services.RoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mynasa/dates")
public class DateController {
    private static final Logger logger = LoggerFactory.getLogger(DateController.class);
    private DateService dateService;

    public DateController(DateService dateService) {
        this.dateService = dateService;
    }
    @GetMapping
    public List<String> getDates() {
        return dateService.getDates();
    }

}
