package com.weng.weather.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Data
@Configuration
@Slf4j
public class WebClientConfig {
    @Autowired
    private OpenMeteoBaseUrl openMeteoBaseUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(logClientRequest())
                .filter(logClientResponse())
                .defaultHeader("Content-Type", "application/json");
    }

    // Log client (WebService) request details
    private ExchangeFilterFunction logClientRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("CLIENT-REQ: {} {}", clientRequest.method(), clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    // Log client (WebService) response details
    private ExchangeFilterFunction logClientResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("CLIENT-RES: {}", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }
}
