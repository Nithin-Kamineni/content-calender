package com.dev.spring.boot.calendar.contentcalendar.controller;

import com.dev.spring.boot.calendar.contentcalendar.config.ContentCalenderProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {
    private final ContentCalenderProperties properties;

//    @Value("${cc.welcomeMessage: Default: Welcome message}")
//    private String welcomeMessage;
//
//    @Value("${cc.about: Default about}")
//    private String about;

//    @GetMapping("/")
//    public Map<String, String> home(){
//        return Map.of("Welcome Message:",welcomeMessage, "about:", about);
//    }
    public HomeController(ContentCalenderProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/")
    public ContentCalenderProperties home(){
        return properties;
    }

}
