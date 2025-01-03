package com.weng.weather.ws.openmeteo;

import com.weng.weather.config.WebClientConfig;
import com.weng.weather.model.dto.external.response.MeteoWeatherCurrentRes;
import com.weng.weather.model.dto.external.response.MeteoWeatherDailyRes;
import com.weng.weather.model.dto.external.response.MeteoWeatherHourlyRes;
import com.weng.weather.ws.GenericServiceClient;
import com.weng.weather.ws.ParamsBuilder;
import com.weng.weather.ws.openmeteo.constant.CurrentWeatherVariable;
import com.weng.weather.ws.openmeteo.constant.DailyWeatherVariable;
import com.weng.weather.ws.openmeteo.constant.HourlyWeatherVariable;
import com.weng.weather.ws.openmeteo.constant.WeatherVariable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class WeatherForecastClient {
    private final String baseUrl;
    private final GenericServiceClient serviceClient;

    @Autowired
    public WeatherForecastClient(WebClientConfig webClientConfig, GenericServiceClient serviceClient) {
        this.baseUrl = webClientConfig.getOpenMeteoBaseUrl().getForecast();
        this.serviceClient = serviceClient;
    }

    public MeteoWeatherDailyRes forecastWeatherDaily(Double latitude, Double longitude, String timezone, Integer forecastDays) {
        Set<DailyWeatherVariable> weatherVariables = Set.of(
                DailyWeatherVariable.SUNSET,
                DailyWeatherVariable.SUNRISE,
                DailyWeatherVariable.TEMPERATURE_MIN,
                DailyWeatherVariable.TEMPERATURE_MAX,
                DailyWeatherVariable.WEATHER_CODE
        );
        ParamsBuilder paramsBuilder = initParamBuilder(latitude, longitude, timezone, weatherVariables);
        if (forecastDays != null)
            paramsBuilder.addParam("forecast_days", forecastDays);
        return fetchWeatherForecast(paramsBuilder, MeteoWeatherDailyRes.class);
    }

    public MeteoWeatherHourlyRes forecastWeatherHourly(Double latitude, Double longitude, String timezone, Integer forecastDays) {
        Set<HourlyWeatherVariable> weatherVariables = Set.of(
                HourlyWeatherVariable.WEATHER_CODE,
                HourlyWeatherVariable.TEMPERATURE,
                HourlyWeatherVariable.HUMIDITY,
                HourlyWeatherVariable.WIND_SPEED,
                HourlyWeatherVariable.PRECIPITATION_PROBABILITY,
                HourlyWeatherVariable.VISIBILITY
        );
        ParamsBuilder paramsBuilder = initParamBuilder(latitude, longitude, timezone, weatherVariables);
        if (forecastDays != null)
            paramsBuilder.addParam("forecast_days", forecastDays);
        return fetchWeatherForecast(paramsBuilder, MeteoWeatherHourlyRes.class);
    }

    public MeteoWeatherCurrentRes forecastWeatherCurrent(Double latitude, Double longitude, String timezone) {
        Set<CurrentWeatherVariable> weatherVariables = Set.of(
                CurrentWeatherVariable.WEATHER_CODE,
                CurrentWeatherVariable.TEMPERATURE,
                CurrentWeatherVariable.HUMIDITY,
                CurrentWeatherVariable.WIND_SPEED,
                CurrentWeatherVariable.WIND_DIRECTION
        );
        ParamsBuilder paramsBuilder = initParamBuilder(latitude, longitude, timezone, weatherVariables);
        return fetchWeatherForecast(paramsBuilder, MeteoWeatherCurrentRes.class);
    }

    // https://open-meteo.com/en/docs
    private <T> T fetchWeatherForecast(ParamsBuilder paramsBuilder, Class<T> responseType) {
        Map<String, String> params = paramsBuilder.build();
        ResponseEntity<T> response = serviceClient.get(baseUrl, "/forecast", responseType, params);
        return response.getBody();
    }

    private ParamsBuilder initParamBuilder(Double latitude, Double longitude, String timezone, Set<? extends WeatherVariable> weatherVariables) {
        return new ParamsBuilder()
                .addParam("latitude", latitude)
                .addParam("longitude", longitude)
                .addParam("timezone", timezone)
                .addParam(weatherVariables);
    }
}
