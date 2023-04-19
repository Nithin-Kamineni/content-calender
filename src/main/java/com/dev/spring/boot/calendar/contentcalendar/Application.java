package com.dev.spring.boot.calendar.contentcalendar;

import com.dev.spring.boot.calendar.contentcalendar.config.ContentCalenderProperties;
import com.dev.spring.boot.calendar.contentcalendar.model.Content;
import com.dev.spring.boot.calendar.contentcalendar.model.Status;
import com.dev.spring.boot.calendar.contentcalendar.model.Type;
import com.dev.spring.boot.calendar.contentcalendar.repository.ContentJdbcTemplateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@EnableConfigurationProperties(ContentCalenderProperties.class)    //doubt
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		RestTemplate restTemplate = (RestTemplate) context.getBean("restTemplate");
		System.out.println(restTemplate);

	}

//	@Bean
//	CommandLineRunner commandLineRunner(ContentJdbcTemplateRepository repository){
//		return args -> {
////			insert data into database
////			System.out.println("hello there");
//			Content content = new Content(
//					null,
//					"My first blog post",
//					"My first blog post" ,
//					Status.IDEA,
//					Type.ARTICLE,
//					LocalDateTime.now(),
//					null,
//					""
//				);
//			repository.save(content);
//
//			};
//		}
	}

