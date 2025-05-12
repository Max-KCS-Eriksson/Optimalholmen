package com.maxeriksson.optimalholmen.client.openmeteo.mapper;

import com.maxeriksson.optimalholmen.client.openmeteo.dto.OpenMeteoDTO;
import com.maxeriksson.optimalholmen.client.openmeteo.model.Hourly;
import com.maxeriksson.optimalholmen.client.openmeteo.model.OpenMeteoApiResponse;
import com.maxeriksson.optimalholmen.model.ForecastDTO;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OpenMeteoMapper {

    private final String forecastSource = "OpenMeteo";

    public ForecastDTO toForecastDTO(OpenMeteoApiResponse openMeteoApiResponse, int hoursAhead) {
        OpenMeteoDTO openMeteoDTO = this.toDto(openMeteoApiResponse, hoursAhead);
        return new ForecastDTO(
                forecastSource,
                openMeteoDTO.dateTime(),
                openMeteoDTO.temperature(),
                openMeteoDTO.windSpeed());
    }

    public OpenMeteoDTO toDto(OpenMeteoApiResponse openMeteoApiResponse, int hoursAhead) {
        int hoursAheadIndex = LocalDateTime.now().getHour() + hoursAhead;
        Hourly hourlyData = openMeteoApiResponse.getHourly();

        LocalDateTime dateTime = LocalDateTime.parse(hourlyData.getTime().get(hoursAheadIndex));
        Double temperature = hourlyData.getTemperature2m().get(hoursAheadIndex);
        Double windSpeed = hourlyData.getWindSpeed10m().get(hoursAheadIndex);

        return new OpenMeteoDTO(dateTime, temperature, windSpeed);
    }
}
