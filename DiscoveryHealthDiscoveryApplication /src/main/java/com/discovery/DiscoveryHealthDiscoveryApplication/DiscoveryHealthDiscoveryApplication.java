package com.discovery.DiscoveryHealthDiscoveryApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryHealthDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryHealthDiscoveryApplication.class, args);
	}

}
