package com.maxeriksson.optimalholmen.client.openmeteo.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/** Measurements are given using the OpenMeteoric system */
public record OpenMeteoDTO(LocalDateTime dateTime, double temperature, double windSpeed) {

    public OpenMeteoDTO {
        Objects.requireNonNull(dateTime);
        Objects.requireNonNull(temperature);
        Objects.requireNonNull(windSpeed);
    }
}
