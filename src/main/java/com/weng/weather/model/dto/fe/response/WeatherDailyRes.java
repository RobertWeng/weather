package com.weng.weather.model.dto.fe.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WeatherDailyRes {
    // Example Response
    private String timezone; // Asia/Kuala_Lumpur
    private String timezoneAbbreviation; // +08
    private List<ForecastDataRes> forecastDataRes;

    @Getter
    @Builder
    public static class ForecastDataRes {
        private String date; // 2025-01-03
        private String dayOfWeek; // Friday
        private String sunrise; // 2025-01-03 07:23 AM
        private String sunset; // 2025-01-03 07:16 PM
        private String temperatureMax; // 32 °C
        private String temperatureMin; // 24 °C
        private Integer weatherCode; // 96
        private String weatherCodeDesc; // Thunderstorm with Hail
    }
}
