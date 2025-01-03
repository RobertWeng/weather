package com.weng.weather.ws.openmeteo.constant;

import lombok.Getter;

@Getter
public enum HourlyWeatherVariable implements WeatherVariable {
    WEATHER_CODE("weather_code"),
    TEMPERATURE("temperature_2m"),
    HUMIDITY("relative_humidity_2m"),
    WIND_SPEED("wind_speed_10m"),
    PRECIPITATION_PROBABILITY("precipitation_probability"),
    VISIBILITY("visibility");

    private final String paramValue;

    HourlyWeatherVariable(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public String getParamKey() {
        return "hourly";
    }
}
