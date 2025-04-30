package com.quick.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quick.cloud"})
public class QuickCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuickCloudApplication.class, args);
    }
}
