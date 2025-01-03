package com.weng.weather.service;

import com.weng.weather.mapper.WeatherForecastMapper;
import com.weng.weather.model.dto.external.response.MeteoLocationRes;
import com.weng.weather.model.dto.external.response.MeteoWeatherCurrentRes;
import com.weng.weather.model.dto.external.response.MeteoWeatherDailyRes;
import com.weng.weather.model.dto.external.response.MeteoWeatherHourlyRes;
import com.weng.weather.model.dto.fe.response.WeatherCurrentRes;
import com.weng.weather.model.dto.fe.response.WeatherDailyRes;
import com.weng.weather.model.dto.fe.response.WeatherHourlyRes;
import com.weng.weather.ws.openmeteo.GeocodingClient;
import com.weng.weather.ws.openmeteo.WeatherForecastClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private GeocodingClient geocodingClient;
    @Autowired
    private WeatherForecastClient weatherForecastClient;
    @Autowired
    private WeatherForecastMapper weatherForecastMapper;

    public WeatherDailyRes forecastWeatherDaily(String cityName, String country, Integer forecastDays) {
        MeteoLocationRes.Result city = geocodingClient.getLocation(cityName.toLowerCase(), country.toLowerCase());
        MeteoWeatherDailyRes res = weatherForecastClient.forecastWeatherDaily(city.getLatitude(), city.getLongitude(), city.getTimezone(), forecastDays);
        return weatherForecastMapper.toDailyRes(res);
    }

    public WeatherHourlyRes forecastWeatherHourly(String cityName, String country, Integer forecastDays) {
        MeteoLocationRes.Result city = geocodingClient.getLocation(cityName.toLowerCase(), country.toLowerCase());
        MeteoWeatherHourlyRes res = weatherForecastClient.forecastWeatherHourly(city.getLatitude(), city.getLongitude(), city.getTimezone(), forecastDays);
        return weatherForecastMapper.toHourlyRes(res);
    }

    public WeatherCurrentRes currentWeather(String cityName, String country) {
        MeteoLocationRes.Result city = geocodingClient.getLocation(cityName.toLowerCase(), country.toLowerCase());
        MeteoWeatherCurrentRes res = weatherForecastClient.forecastWeatherCurrent(city.getLatitude(), city.getLongitude(), city.getTimezone());
        return weatherForecastMapper.toCurrentRes(res);
    }
}
