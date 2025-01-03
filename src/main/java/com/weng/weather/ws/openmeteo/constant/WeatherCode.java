package com.weng.weather.ws.openmeteo.constant;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
//WMO Weather interpretation codes (WW)
public enum WeatherCode {
    CLEAR_SKY(0, "Clear Sky"),
    MAINLY_CLEAR(1, "Mainly Clear"),
    PARTLY_CLOUDY(2, "Partly Cloudy"),
    OVERCAST(3, "Overcast"),
    FOG(45, "Fog"),
    RIME_FOG(48, "Rime fog"),
    DRIZZLE_LIGHT(51, "Light Drizzle"),
    DRIZZLE_MODERATE(53, "Moderate Drizzle"),
    DRIZZLE_DENSE(55, "Dense Drizzle"),
    FREEZING_DRIZZLE_LIGHT(56, "Light Freezing Drizzle"),
    FREEZING_DRIZZLE_DENSE(57, "Dense Freezing Drizzle"),
    RAIN_SLIGHT(61, "Slight Rain"),
    RAIN_MODERATE(63, "Moderate Rain"),
    RAIN_HEAVY(65, "Heavy Rain"),
    FREEZING_RAIN_LIGHT(66, "Light Freezing Rain"),
    FREEZING_RAIN_HEAVY(67, "Heavy Freezing Rain"),
    SNOW_FALL_SLIGHT(71, "Slight Snow Fall"),
    SNOW_FALL_MODERATE(73, "Moderate Snow Fall"),
    SNOW_FALL_HEAVY(75, "Heavy Snow Fall"),
    SNOW_GRAINS(77, "Snow Grains"),
    RAIN_SHOWERS_SLIGHT(80, "Slight Rain Showers"),
    RAIN_SHOWERS_MODERATE(81, "Moderate Rain Showers"),
    RAIN_SHOWERS_VIOLENT(82, "Violent Rain Showers"),
    SNOW_SHOWERS_SLIGHT(85, "Slight Snow Showers"),
    SNOW_SHOWERS_HEAVY(86, "Heavy Snow Showers"),
    THUNDERSTORM(95, "Thunderstorm"),
    THUNDERSTORM_HAIL(96, "Thunderstorm with Hail"),
    UNKNOWN(-1, "Unknown Weather"); // Default case

    private final int code;
    private final String description;

    // Cached map for fast lookup
    // Map<Integer, WeatherCode enum>
    private static final Map<Integer, WeatherCode> CODE_MAP = Stream.of(values())
            .collect(Collectors.toMap(WeatherCode::getCode, weatherCode -> weatherCode));

    WeatherCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescriptionByCode(int code) {
        return CODE_MAP.getOrDefault(code, UNKNOWN).getDescription();
    }
}

