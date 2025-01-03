package com.weng.weather.service;

import com.weng.weather.mapper.GeocodingMapper;
import com.weng.weather.model.dto.external.response.MeteoLocationRes;
import com.weng.weather.model.dto.fe.response.LocationRes;
import com.weng.weather.ws.openmeteo.GeocodingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private GeocodingClient geocodingClient;
    @Autowired
    private GeocodingMapper geocodingMapper;

    public LocationRes getLocation(String cityName, String country) {
        MeteoLocationRes.Result res = geocodingClient.getLocation(cityName.toLowerCase(), country.toLowerCase());
        return geocodingMapper.toLocationRes(res);
    }
}
