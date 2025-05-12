package com.maxeriksson.optimalholmen.client.met.mapper;

import com.maxeriksson.optimalholmen.client.met.dto.MetDTO;
import com.maxeriksson.optimalholmen.client.met.model.Details;
import com.maxeriksson.optimalholmen.client.met.model.MetApiResponse;
import com.maxeriksson.optimalholmen.client.met.model.Timeseries;
import com.maxeriksson.optimalholmen.model.ForecastDTO;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MetMapper {

    private final String forecastSource = "MET";

    public ForecastDTO toForecastDTO(MetApiResponse metApiResponse, int hoursAhead) {
        MetDTO metDTO = this.toDto(metApiResponse, hoursAhead);
        return new ForecastDTO(
                forecastSource, metDTO.dateTime(), metDTO.temperature(), metDTO.windSpeed());
    }

    public MetDTO toDto(MetApiResponse metApiResponse, int hoursAhead) {
        int currentHourIndex = 2;
        Timeseries timeseries =
                metApiResponse.getProperties().getTimeseries().get(currentHourIndex + hoursAhead);
        Details details = timeseries.getData().getInstant().getDetails();

        LocalDateTime dateTime =
                LocalDateTime.parse(
                        timeseries.getTime().substring(0, timeseries.getTime().length() - 1));
        Double temperature = details.getAirTemperature();
        Double windSpeed = details.getWindSpeed();

        return new MetDTO(dateTime, temperature, windSpeed);
    }
}
