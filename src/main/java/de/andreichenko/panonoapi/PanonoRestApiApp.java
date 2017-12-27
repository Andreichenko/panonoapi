package de.andreichenko.panonoapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"de.andreichenko.panonoapi"})
public class PanonoRestApiApp {

    public static void main(String[] args) {
        SpringApplication.run(PanonoRestApiApp.class, args);
    }
}
