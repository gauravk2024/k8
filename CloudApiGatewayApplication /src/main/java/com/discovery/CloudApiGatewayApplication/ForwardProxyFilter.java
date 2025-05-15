package com.discovery.CloudApiGatewayApplication;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.net.URI;

@Component
public class ForwardProxyFilter extends AbstractGatewayFilterFactory<ForwardProxyFilter.Config> {

    public ForwardProxyFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String originalPath = request.getURI().getRawPath();

            String targetUrl = originalPath.replaceFirst("/proxy/", "https://");
            ServerHttpRequest newRequest = request.mutate()
                    .uri(URI.create(targetUrl))
                    .header(HttpHeaders.HOST, URI.create(targetUrl).getHost())
                    .build();

            return chain.filter(exchange.mutate().request(newRequest).build());
        };
    }

    public static class Config {
    }
}
