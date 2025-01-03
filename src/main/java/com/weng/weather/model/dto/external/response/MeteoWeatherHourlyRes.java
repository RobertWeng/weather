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
public class MeteoWeatherHourlyRes {

    private Double latitude;
    private Double longitude;
    private String timezone;
    private String timezoneAbbreviation;
    private HourlyUnits hourlyUnits;
    private Hourly hourly;

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class HourlyUnits {
        private String time;
        @JsonProperty("temperature_2m")
        private String temperature2m;
        @JsonProperty("relative_humidity_2m")
        private String relativeHumidity2m;
        private String precipitationProbability;
        private String weatherCode;
        private String visibility;
        @JsonProperty("wind_speed_10m")
        private String windSpeed10m;
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Hourly {
        private List<String> time;
        @JsonProperty("temperature_2m")
        private List<Double> temperature2m;
        @JsonProperty("relative_humidity_2m")
        private List<Integer> relativeHumidity2m;
        @JsonProperty("precipitation_probability")
        private List<Integer> precipitationProbability;
        private List<Integer> weatherCode;
        private List<Double> visibility;
        @JsonProperty("wind_speed_10m")
        private List<Double> windSpeed10m;
    }
}

