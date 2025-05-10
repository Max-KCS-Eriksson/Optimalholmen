package com.maxeriksson.optimalholmen.model;

import java.time.LocalDateTime;
import java.util.Objects;

/** Measurements are given using the Metric system */
public record ForecastDTO(
        String forecastSource, LocalDateTime dateTime, double temperature, double windSpeed) {

    public ForecastDTO {
        Objects.requireNonNull(forecastSource);
        Objects.requireNonNull(dateTime);
        Objects.requireNonNull(temperature);
        Objects.requireNonNull(windSpeed);
    }
}
