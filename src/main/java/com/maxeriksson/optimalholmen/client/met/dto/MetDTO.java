package com.maxeriksson.optimalholmen.client.met.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/** Measurements are given using the Metric system */
public record MetDTO(LocalDateTime dateTime, double temperature, double windSpeed) {

    public MetDTO {
        Objects.requireNonNull(dateTime);
        Objects.requireNonNull(temperature);
        Objects.requireNonNull(windSpeed);
    }
}
