package com.dev.spring.boot.calendar.contentcalendar.config;


import com.dev.spring.boot.calendar.contentcalendar.model.Content;
import com.dev.spring.boot.calendar.contentcalendar.repository.ContentJdbcTemplateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


//will run after dependency injection
//@Profile("!dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final ContentJdbcTemplateRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(ContentJdbcTemplateRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
//    @Bean
    public void run(String... args) throws Exception {
                ClassPathResource resource = new ClassPathResource("/data/contents.json");
                byte[] fileData = resource.getInputStream().readAllBytes();
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                List<Content> contents = objectMapper.readValue(fileData, typeFactory.constructCollectionType(List.class, Content.class));
                repository.saveAll(contents);
        }
    }
