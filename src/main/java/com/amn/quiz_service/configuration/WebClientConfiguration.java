package com.amn.quiz_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class  WebClientConfiguration {
    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }

}
