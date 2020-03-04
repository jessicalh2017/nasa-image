package com.nasa.exercise.services;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DateServiceImpl implements DateService {

    private static final Logger logger = LoggerFactory.getLogger(DateServiceImpl.class);
    private static List<String> dateList = new ArrayList<String>();
    private static final String source = "./dates.txt";

    @PostConstruct
    public void initialize() {
        String[] initialFormats = {
                "MM/dd/yy",
                "MMM d, yyyy",
                "MMM-d-yyyy"
        };

        try (Stream<String> stream = Files.lines(Paths.get(source))) {

            stream.forEach(date -> {
                try {
                    //validate each date in the source file
                    Date parsedDate = DateUtils.parseDateStrictly(date, initialFormats);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    dateList.add(formatter.format(parsedDate));
                } catch (ParseException exp) {
                    logger.error("Skip invalid date {}", date);
                }
            });

        } catch (IOException e) {
            logger.error("Cannot read file");
        }

    }

    @Override
    public List<String> getDates() {
        return dateList;
    }


}
