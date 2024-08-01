package com.apurba.airline.Configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class LogDirectoryConfig {

    @PostConstruct
    public void init() {

        String logDirectoryPath = "app_log";


        File logDirectory = new File(logDirectoryPath);
        if (!logDirectory.exists()) {
            if (logDirectory.mkdirs()) {
                System.out.println("Log directory created: " + logDirectoryPath);
            } else {
                System.err.println("Failed to create log directory: " + logDirectoryPath);
            }
        }
    }
}
