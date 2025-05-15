package com.example.master.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/proxy")
public class ForwardProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/**")
    public ResponseEntity<String> forwardRequest(
            @RequestHeader HttpHeaders headers,
            @RequestBody(required = false) String body,
            HttpMethod method,
            @RequestParam(value = "url") String externalUrl) {

        try {
            if (!externalUrl.startsWith("http://") && !externalUrl.startsWith("https://")) {
                externalUrl = "https://" + externalUrl;
            }

            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    URI.create(externalUrl),
                    method,
                    requestEntity,
                    String.class
            );

            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error forwarding request: " + e.getMessage());
        }
    }
}
