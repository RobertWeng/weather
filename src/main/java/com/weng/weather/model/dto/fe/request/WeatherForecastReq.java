package com.weng.weather.model.dto.fe.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForecastReq extends CityReq {
    @Min(1)
    @Max(14)
    private Integer forecastDays;
}
