package com.weng.weather.mapper;

import com.weng.weather.model.dto.external.response.MeteoWeatherCurrentRes;
import com.weng.weather.model.dto.external.response.MeteoWeatherDailyRes;
import com.weng.weather.model.dto.external.response.MeteoWeatherHourlyRes;
import com.weng.weather.model.dto.fe.response.WeatherCurrentRes;
import com.weng.weather.model.dto.fe.response.WeatherDailyRes;
import com.weng.weather.model.dto.fe.response.WeatherDailyRes.ForecastDataRes;
import com.weng.weather.model.dto.fe.response.WeatherHourlyRes;
import com.weng.weather.ws.openmeteo.constant.WeatherCode;
import com.weng.weather.ws.openmeteo.constant.WindDirection;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring")
public abstract class WeatherForecastMapper {
    public WeatherDailyRes toDailyRes(MeteoWeatherDailyRes data) {
        var forecastDataResList =
                combineDataListsDaily(data.getDailyUnits(), data.getDaily());
        return WeatherDailyRes.builder()
                .timezone(data.getTimezone())
                .timezoneAbbreviation(data.getTimezoneAbbreviation())
                .forecastDataRes(forecastDataResList)
                .build();
    }

    public WeatherHourlyRes toHourlyRes(MeteoWeatherHourlyRes data) {
        var forecastDataResList =
                combineDataListsHourly(data.getHourlyUnits(), data.getHourly());
        return WeatherHourlyRes.builder()
                .timezone(data.getTimezone())
                .timezoneAbbreviation(data.getTimezoneAbbreviation())
                .forecastDataRes(forecastDataResList)
                .build();
    }

    public WeatherCurrentRes toCurrentRes(MeteoWeatherCurrentRes data) {
        var forecastDataResList =
                combineDataListsCurrent(data.getCurrentUnits(), data.getCurrent());
        return WeatherCurrentRes.builder()
                .timezone(data.getTimezone())
                .timezoneAbbreviation(data.getTimezoneAbbreviation())
                .forecastDataRes(forecastDataResList)
                .build();
    }

    private List<ForecastDataRes> combineDataListsDaily(MeteoWeatherDailyRes.DailyUnits units, MeteoWeatherDailyRes.Daily data) {
        List<ForecastDataRes> res = new ArrayList<>();
        List<String> dates = data.getTime();

        for (int i = 0; i < dates.size(); i++) {
            Integer weatherCode = data.getWeatherCode().get(i);
            String date = dates.get(i);
            WeatherDailyRes.ForecastDataRes dataRes = WeatherDailyRes.ForecastDataRes.builder()
                    .date(date)
                    .dayOfWeek(getDayOfWeekName(date))
                    .sunrise(formatDateTime(data.getSunrise().get(i)))
                    .sunset(formatDateTime(data.getSunset().get(i)))
                    .temperatureMax(String.format("%.0f %s", data.getTemperature2mMax().get(i), units.getTemperature2mMax()))
                    .temperatureMin(String.format("%.0f %s", data.getTemperature2mMin().get(i), units.getTemperature2mMin()))
                    .weatherCode(weatherCode)
                    .weatherCodeDesc(WeatherCode.getDescriptionByCode(weatherCode))
                    .build();
            res.add(dataRes);
        }
        return res;
    }

    private List<WeatherHourlyRes.ForecastDataRes> combineDataListsHourly(MeteoWeatherHourlyRes.HourlyUnits units, MeteoWeatherHourlyRes.Hourly data) {
        List<WeatherHourlyRes.ForecastDataRes> res = new ArrayList<>();
        List<String> dates = data.getTime();

        for (int i = 0; i < dates.size(); i++) {
            Integer weatherCode = data.getWeatherCode().get(i);
            String date = dates.get(i);
            WeatherHourlyRes.ForecastDataRes dataRes = WeatherHourlyRes.ForecastDataRes.builder()
                    .date(LocalDateTime.parse(date).toLocalDate().toString())
                    .dayOfWeek(getDayOfWeekName(date))
                    .temperature(String.format("%.0f %s", data.getTemperature2m().get(i), units.getTemperature2m()))
                    .humidity(String.format("%d%s", data.getRelativeHumidity2m().get(i), units.getRelativeHumidity2m()))
                    .precipitationProbability(String.format("%d%s", data.getPrecipitationProbability().get(i), units.getPrecipitationProbability()))
                    .weatherCode(weatherCode)
                    .weatherCodeDesc(WeatherCode.getDescriptionByCode(weatherCode))
                    .visibility(String.format("%.1f %s", data.getVisibility().get(i), units.getVisibility()))
                    .windSpeed(String.format("%.1f %s", data.getWindSpeed10m().get(i), units.getWindSpeed10m()))
                    .build();
            res.add(dataRes);
        }
        return res;
    }

    private WeatherCurrentRes.ForecastDataRes combineDataListsCurrent(MeteoWeatherCurrentRes.CurrentUnits units, MeteoWeatherCurrentRes.Current data) {
        Integer weatherCode = data.getWeatherCode();
        String date = data.getTime();
        return WeatherCurrentRes.ForecastDataRes.builder()
                .date(formatDateTime(date))
                .dayOfWeek(getDayOfWeekName(date))
                .temperature(String.format("%.0f %s", data.getTemperature2m(), units.getTemperature2m()))
                .humidity(String.format("%d%s", data.getRelativeHumidity2m(), units.getRelativeHumidity2m()))
                .windSpeed(String.format("%.1f %s", data.getWindSpeed10m(), units.getWindSpeed10m()))
                .windDirection(String.format("%d%s %s", data.getWindDirection10m(), units.getWindDirection10m(), WindDirection.degreeToDirection(data.getWindDirection10m())))
                .weatherCode(weatherCode)
                .weatherCodeDesc(WeatherCode.getDescriptionByCode(weatherCode))
                .build();
    }

    private String formatDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return LocalDateTime.parse(dateTime).format(formatter);
    }

    private String getDayOfWeekName(String date) {
        try {
            return LocalDate.parse(date).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(date).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        } catch (Exception e) {
            return date;
        }
    }
}
