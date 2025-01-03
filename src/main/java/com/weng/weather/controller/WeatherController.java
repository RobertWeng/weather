package com.weng.weather.controller;

import com.weng.weather.model.dto.fe.request.CityReq;
import com.weng.weather.model.dto.fe.request.WeatherForecastReq;
import com.weng.weather.model.dto.fe.response.WeatherCurrentRes;
import com.weng.weather.model.dto.fe.response.WeatherDailyRes;
import com.weng.weather.model.dto.fe.response.WeatherHourlyRes;
import com.weng.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
@Validated
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Operation(summary = "Forecast Weather Daily", description = "This API is used to retrieve the daily weather forecast data for a city")
    @PostMapping("/forecast/daily")
    public ResponseEntity<WeatherDailyRes> forecastDaily(@Valid @RequestBody WeatherForecastReq req) {
        var res = weatherService.forecastWeatherDaily(req.getCity(), req.getCountry(), req.getForecastDays());
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Forecast Weather Hourly", description = "This API is used to retrieve the hourly weather forecast data for a city")
    @PostMapping("/forecast/hourly")
    public ResponseEntity<WeatherHourlyRes> forecastHourly(@Valid @RequestBody WeatherForecastReq req) {
        var res = weatherService.forecastWeatherHourly(req.getCity(), req.getCountry(), req.getForecastDays());
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Get Current Weather", description = "This API is used to retrieve the current weather data for a city")
    @PostMapping("/now")
    public ResponseEntity<WeatherCurrentRes> currentWeather(@Valid @RequestBody CityReq req) {
        var res = weatherService.currentWeather(req.getCity(), req.getCountry());
        return ResponseEntity.ok(res);
    }
}
