package com.aston.rapidride.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class TestController {

    @GetMapping
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public String pong() {
        return "Pong!";
    }
}
