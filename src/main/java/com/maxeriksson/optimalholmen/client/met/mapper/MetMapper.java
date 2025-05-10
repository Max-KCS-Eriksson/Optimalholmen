package com.maxeriksson.optimalholmen.client.met.mapper;

import com.maxeriksson.optimalholmen.client.met.dto.MetDTO;
import com.maxeriksson.optimalholmen.client.met.model.Details;
import com.maxeriksson.optimalholmen.client.met.model.Met;
import com.maxeriksson.optimalholmen.client.met.model.Timeseries;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MetMapper {

    public MetDTO toDto(Met met, int hoursAhead) {
        int currentHourIndex = 2;
        Timeseries timeseries =
                met.getProperties().getTimeseries().get(currentHourIndex + hoursAhead);
        Details details = timeseries.getData().getInstant().getDetails();

        LocalDateTime dateTime =
                LocalDateTime.parse(
                        timeseries.getTime().substring(0, timeseries.getTime().length() - 1));
        Double temperature = details.getAirTemperature();
        Double windSpeed = details.getWindSpeed();

        return new MetDTO(dateTime, temperature, windSpeed);
    }
}
