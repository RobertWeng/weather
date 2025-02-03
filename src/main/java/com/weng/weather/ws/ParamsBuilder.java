package com.weng.weather.ws;

import com.weng.weather.ws.openmeteo.constant.WeatherVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ParamsBuilder {
    private final Map<String, String> params = new HashMap<>();

    public ParamsBuilder addParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    public ParamsBuilder addParam(String key, int value) {
        params.put(key, String.valueOf(value));
        return this;
    }

    public ParamsBuilder addParam(String key, double value) {
        params.put(key, String.valueOf(value));
        return this;
    }

    public <T extends WeatherVariable> ParamsBuilder addParam(Set<T> weatherVariable) {
        String key = weatherVariable.iterator().next().getParamKey(); // Set key based on WeatherVariable getParamKey: current, daily, hourly
        String value = weatherVariable.stream()
                .map(WeatherVariable::getParamValue)
                .collect(Collectors.joining(",")); // Combine paramValue with comma
        params.put(key, value);
        return this;
    }

    public Map<String, String> build() {
        return params;
    }
}
