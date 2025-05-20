package com.discovery.CloudApiGatewayApplication.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class KubernetesToken extends AbstractGatewayFilterFactory<KubernetesToken.Config> {
    private static final String TOKEN_PATH = "/var/run/secrets/kubernetes.io/serviceaccount/token";

    public KubernetesToken() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            try {
                String token = new String(Files.readAllBytes(Paths.get(TOKEN_PATH)));
                ServerWebExchange mutatedExchange = exchange.mutate()
                        .request(r -> r.headers(headers -> headers.setBearerAuth(token)))
                        .build();
                return chain.filter(mutatedExchange);
            } catch (IOException e) {
                return Mono.error(new RuntimeException("Failed to read Kubernetes token", e));
            }
        };
    }

    public static class Config {
        // empty
    }
}

