package com.dev.spring.boot.calendar.contentcalendar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "cc")
public record ContentCalenderProperties(String welcomeMessage, String about) {
}
