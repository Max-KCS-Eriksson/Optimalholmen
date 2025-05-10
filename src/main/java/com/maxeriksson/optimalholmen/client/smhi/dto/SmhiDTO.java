package com.maxeriksson.optimalholmen.client.smhi.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/** Measurements are given using the Metric system */
public record SmhiDTO(LocalDateTime dateTime, double temperature, double windSpeed) {

    public SmhiDTO {
        Objects.requireNonNull(dateTime);
        Objects.requireNonNull(temperature);
        Objects.requireNonNull(windSpeed);
    }
}
