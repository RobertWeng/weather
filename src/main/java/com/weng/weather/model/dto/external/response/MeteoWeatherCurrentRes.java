package com.weng.weather.model.dto.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MeteoWeatherCurrentRes {

    private double latitude;
    private double longitude;
    private String timezone;
    private String timezoneAbbreviation;
    private CurrentUnits currentUnits;
    private Current current;

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class CurrentUnits {
        private String time;
        @JsonProperty("temperature_2m")
        private String temperature2m;
        @JsonProperty("relative_humidity_2m")
        private String relativeHumidity2m;
        private String weatherCode;
        @JsonProperty("wind_speed_10m")
        private String windSpeed10m;
        @JsonProperty("wind_direction_10m")
        private String windDirection10m;
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Current {
        private String time;
        @JsonProperty("temperature_2m")
        private Double temperature2m;
        @JsonProperty("relative_humidity_2m")
        private Integer relativeHumidity2m;
        private Integer weatherCode;
        @JsonProperty("wind_speed_10m")
        private Double windSpeed10m;
        @JsonProperty("wind_direction_10m")
        private Integer windDirection10m;
    }
}
