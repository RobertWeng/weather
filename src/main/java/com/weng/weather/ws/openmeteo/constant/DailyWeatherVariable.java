package com.weng.weather.ws.openmeteo.constant;

import lombok.Getter;

@Getter
public enum DailyWeatherVariable implements WeatherVariable {
    WEATHER_CODE("weather_code"),
    TEMPERATURE_MAX("temperature_2m_max"),
    TEMPERATURE_MIN("temperature_2m_min"),
    SUNRISE("sunrise"),
    SUNSET("sunset");

    private final String paramValue;

    DailyWeatherVariable(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public String getParamKey() {
        return "daily";
    }
}
