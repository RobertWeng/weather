package com.weng.weather.model.dto.fe.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WeatherHourlyRes {
    private String timezone; // Asia/Kuala_Lumpur
    private String timezoneAbbreviation; // +08
    private List<ForecastDataRes> forecastDataRes;

    @Getter
    @Builder
    public static class ForecastDataRes {
        private String date; // 2025-01-03
        private String dayOfWeek; // Friday
        private String temperature; // 26 °C
        private String humidity; // 93%
        private String precipitationProbability; // 0%
        private String visibility; // 24140.0 m
        private String windSpeed; // 4.3 km/h
        private Integer weatherCode; // 3
        private String weatherCodeDesc; // Overcast
    }
}
