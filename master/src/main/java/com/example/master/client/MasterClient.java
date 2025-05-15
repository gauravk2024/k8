package com.example.master.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "master-service", path = "/api/master")
public interface MasterClient {
}
