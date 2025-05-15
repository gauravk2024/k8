package com.example.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ServiceAController {

    @GetMapping
    public Map<String, String> getUser() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from User Service!");
        response.put("status", "OK");
        return response;
    }
}