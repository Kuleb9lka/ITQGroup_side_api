package com.ITQGroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ItqGroupApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItqGroupApplication.class, args);
    }

}
