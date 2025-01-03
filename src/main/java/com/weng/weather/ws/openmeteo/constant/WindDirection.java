package com.weng.weather.ws.openmeteo.constant;

public class WindDirection {

    // Array representing the 8 main compass directions
    private static final String[] DIRECTIONS = {
            "North", "North-East", "East", "South-East",
            "South", "South-West", "West", "North-West"
    };

    public static String degreeToDirection(int degree) {
        // Validate the input degree
        if (degree < 0 || degree > 360) {
            throw new IllegalArgumentException("Degree must be between 0 and 360.");
        }

        // Divide the degree by 45 to determine its segment
        // Use Math.round to ensure accurate rounding
        int index = Math.round(degree / 45.0f) % 8;

        // Map the calculated index to the corresponding direction
        return DIRECTIONS[index];
    }
}

