package com.weng.weather.model.dto.fe.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationRes {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String countryCode;
    private String timezone;
    private String country;
}
