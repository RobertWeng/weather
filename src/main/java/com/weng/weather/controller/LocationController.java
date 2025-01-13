package com.weng.weather.controller;

import com.weng.weather.model.dto.fe.response.LocationRes;
import com.weng.weather.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/locations")
@Validated
@Slf4j
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Operation(summary = "Search Location", description = "This API is used to retrieve city latitude, longitude and other information")
    @GetMapping("/search")
    public ResponseEntity<LocationRes> searchLocations(@RequestParam String city, @RequestParam String country) {
        log.info("Test Start");
        LocationRes res = locationService.getLocation(city, country);
        log.info("Test End");
        return ResponseEntity.ok(res);
    }
}
