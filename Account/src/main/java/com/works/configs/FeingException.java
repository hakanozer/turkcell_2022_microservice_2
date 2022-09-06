package com.works.configs;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeingException {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomDecoder();
    }


}
