package com.example.expense.tracker.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    @ResponseBody
    public String helloWorld() {
        return "Hello, World!";
    }
}
