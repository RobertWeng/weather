package com.weng.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.host.open-meteo")
public class OpenMeteoBaseUrl {
    private String geocoding;
    private String forecast;
    private String history;
}
