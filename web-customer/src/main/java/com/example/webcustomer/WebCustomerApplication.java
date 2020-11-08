package com.example.webcustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//表明自己是消费者
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.example.*"})
public class WebCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCustomerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
