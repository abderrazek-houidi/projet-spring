package com.fst.gestioncours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionCoursApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionCoursApplication.class, args);
    }
}
