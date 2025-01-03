package com.weng.weather.model.dto.external.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeteoLocationRes {
    private List<Result> results;

    @Getter
    public static class Result {
        private Long id;
        private String name;
        private Double latitude;
        private Double longitude;
        private Double elevation;
        private String feature_code;
        private String country_code;
        private Integer admin1_id;
        private Integer admin2_id;
        private Integer admin3_id;
        private Integer admin4_id;
        private String timezone;
        private Integer population;
        private List<String> postcodes;
        private Integer country_id;
        private String country;
        private String admin1;
        private String admin2;
        private String admin3;
        private String admin4;
    }
}
