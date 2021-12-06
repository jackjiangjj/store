package com.jw.store.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StoreAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreAuthApplication.class, args);
    }

}
