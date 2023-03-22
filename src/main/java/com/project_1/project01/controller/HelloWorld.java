package com.project_1.project01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @Value("${welcome.message}")
    private String welcomeMessage;

    @GetMapping(value = "/")
    public String getName(){
        return welcomeMessage;
    }
}
