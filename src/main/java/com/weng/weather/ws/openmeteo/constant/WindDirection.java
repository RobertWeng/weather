package com.weng.weather.ws.openmeteo.constant;

import com.weng.weather.exception.Catch;
import com.weng.weather.exception.Error;

public class WindDirection {

    // Array representing the 8 main compass directions
    private static final String[] DIRECTIONS = {
            "North", "North-East", "East", "South-East",
            "South", "South-West", "West", "North-West"
    };

    public static String degreeToDirection(int degree) {
        // Validate the input degree
        if (degree < 0 || degree > 360) {
            throw Catch.invalidRequest(Error.Msg.INVALID_WIND_DIRECTION);
        }

        // Divide the degree by 45 to determine its segment
        // Use Math.round to ensure accurate rounding
        int index = Math.round(degree / 45.0f) % 8;

        // Map the calculated index to the corresponding direction
        return DIRECTIONS[index];
    }
}

