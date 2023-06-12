package com.javarush.springproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.javarush.springproject1")
public class SpringProject1Application {


    public static void main(String[] args) {
        SpringApplication.run(SpringProject1Application.class, args);

    }

}
