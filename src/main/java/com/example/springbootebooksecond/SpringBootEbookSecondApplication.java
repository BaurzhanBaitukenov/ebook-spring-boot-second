package com.example.springbootebooksecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude = { SecurityAutoConfiguration.class }
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringBootEbookSecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEbookSecondApplication.class, args);
    }

}
