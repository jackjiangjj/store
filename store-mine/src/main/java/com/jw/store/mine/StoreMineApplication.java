package com.jw.store.mine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StoreMineApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreMineApplication.class, args);
    }

}
