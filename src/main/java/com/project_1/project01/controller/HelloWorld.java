package com.project_1.project01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping(value = "/")
    public String getName(){
        return "<h1>Hello World</h1>";
    }
}
