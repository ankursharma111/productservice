package com.ankur.bms.productservice.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    // /hello/say
    @GetMapping("/say")
    public String sayHello(){

    return "Hello";


    }
}
