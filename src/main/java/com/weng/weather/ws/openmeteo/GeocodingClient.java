package com.weng.weather.ws.openmeteo;

import com.weng.weather.config.WebClientConfig;
import com.weng.weather.exception.Catch;
import com.weng.weather.exception.Error;
import com.weng.weather.model.dto.external.response.MeteoLocationRes;
import com.weng.weather.ws.GenericServiceClient;
import com.weng.weather.ws.ParamsBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class GeocodingClient {
    private static final int TOTAL_RESULT = 3;

    private final String baseUrl;
    private final GenericServiceClient serviceClient;

    @Autowired
    public GeocodingClient(WebClientConfig webClientConfig, GenericServiceClient serviceClient) {
        this.baseUrl = webClientConfig.getOpenMeteoBaseUrl().getGeocoding();
        this.serviceClient = serviceClient;
    }

    // https://open-meteo.com/en/docs/geocoding-api
//    @Cacheable(value = "geocodingCache", key = "'geocode:' + #cityName + ':' + #country")
    public MeteoLocationRes.Result getLocation(String cityName, String country) {
        Map<String, String> params = new ParamsBuilder()
                .addParam("name", cityName)
                .addParam("count", TOTAL_RESULT)
                .build();
        ResponseEntity<MeteoLocationRes> res = serviceClient.get(baseUrl, "/search", MeteoLocationRes.class, params);
        if (res.getBody() == null || res.getBody().getResults() == null) throw Catch.wsConnectFailed();
        return res.getBody().getResults()
                .stream()
                .filter(result -> country.equalsIgnoreCase(result.getCountry()))
                .findFirst()
                .orElseThrow(() -> Catch.invalidRequest(Error.Msg.INVALID_COUNTRY));
    }
}
