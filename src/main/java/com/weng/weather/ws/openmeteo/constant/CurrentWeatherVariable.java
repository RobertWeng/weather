package com.weng.weather.ws.openmeteo.constant;

import lombok.Getter;

@Getter
public enum CurrentWeatherVariable implements WeatherVariable {
    WEATHER_CODE("weather_code"),
    TEMPERATURE("temperature_2m"),
    HUMIDITY("relative_humidity_2m"),
    WIND_SPEED("wind_speed_10m"),
    WIND_DIRECTION("wind_direction_10m");
    private final String paramValue;

    CurrentWeatherVariable(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public String getParamKey() {
        return "current";
    }
}
