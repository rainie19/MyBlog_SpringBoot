package com.macie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyblogSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogSpringbootApplication.class, args);
    }

}
