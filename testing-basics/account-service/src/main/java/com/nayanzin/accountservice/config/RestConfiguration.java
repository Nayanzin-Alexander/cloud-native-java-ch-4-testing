package com.nayanzin.accountservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Bean
    protected RestTemplate restTemplate(@Autowired RestTemplateBuilder builder) {
        return builder.build();
    }
}
