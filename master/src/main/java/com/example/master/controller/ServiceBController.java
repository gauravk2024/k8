package com.example.master.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/master")
public class ServiceBController {

    @GetMapping
    public Map<String, String> getMaster() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Master Service!");
        response.put("status", "OK");
        return response;
    }
}
