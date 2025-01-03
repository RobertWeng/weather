package com.weng.weather.model.dto.fe.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityReq {
    @NotBlank
    private String city;

    @NotBlank
    private String country;
}
