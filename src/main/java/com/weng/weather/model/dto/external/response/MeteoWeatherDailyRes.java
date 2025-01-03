package com.weng.weather.model.dto.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MeteoWeatherDailyRes {

    private Double latitude;
    private Double longitude;
    private String timezone;
    private String timezoneAbbreviation;
    private DailyUnits dailyUnits;
    private Daily daily;

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class DailyUnits {
        private String time;
        private String sunrise;
        private String sunset;
        @JsonProperty("temperature_2m_max")
        private String temperature2mMax;
        private String weatherCode;
        @JsonProperty("temperature_2m_min")
        private String temperature2mMin;
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Daily {
        private List<String> time;
        private List<String> sunrise;
        private List<String> sunset;
        @JsonProperty("temperature_2m_max")
        private List<Double> temperature2mMax;
        private List<Integer> weatherCode;
        @JsonProperty("temperature_2m_min")
        private List<Double> temperature2mMin;
    }
}

