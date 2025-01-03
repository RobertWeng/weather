package com.weng.weather.model.dto.fe.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherCurrentRes {
    private String timezone; // Asia/Kuala_Lumpur
    private String timezoneAbbreviation; // +08
    private ForecastDataRes forecastDataRes;

    @Getter
    @Builder
    public static class ForecastDataRes {
        private String date; // 2025-01-03
        private String dayOfWeek; // Friday
        private String temperature; // 26 °C
        private String humidity; // 93%
        private String windSpeed; // 4.3 km/h
        private String windDirection;
        private Integer weatherCode; // 3
        private String weatherCodeDesc; // Overcast
    }
}
