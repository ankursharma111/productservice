package com.ankur.bms.productservice.configs;

import org.springframework.boot.web.client.*;
import org.springframework.context.annotation.*;
import org.springframework.web.client.*;


@Configuration
public class ApplicationConfiguration {


    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplateBuilder().build();

    }







}
