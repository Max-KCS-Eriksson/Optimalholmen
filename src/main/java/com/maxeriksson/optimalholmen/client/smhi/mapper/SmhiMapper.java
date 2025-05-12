package com.maxeriksson.optimalholmen.client.smhi.mapper;

import com.maxeriksson.optimalholmen.client.smhi.dto.SmhiDTO;
import com.maxeriksson.optimalholmen.client.smhi.model.Parameter;
import com.maxeriksson.optimalholmen.client.smhi.model.SmhiApiResponse;
import com.maxeriksson.optimalholmen.client.smhi.model.TimeSeries;
import com.maxeriksson.optimalholmen.model.ForecastDTO;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SmhiMapper {

    private final String forecastSource = "SMHI";

    public ForecastDTO toForecastDTO(SmhiApiResponse smhi, int hoursAhead) {
        SmhiDTO smhiDTO = this.toDto(smhi, hoursAhead);
        return new ForecastDTO(
                forecastSource, smhiDTO.dateTime(), smhiDTO.temperature(), smhiDTO.windSpeed());
    }

    public SmhiDTO toDto(SmhiApiResponse smhi, int hoursAhead) {
        int currentHourIndex = 2;
        TimeSeries timeSeries = smhi.getTimeSeries().get(currentHourIndex + hoursAhead);
        List<Parameter> parameters = timeSeries.getParameters();

        LocalDateTime dateTime =
                LocalDateTime.parse(
                        timeSeries
                                .getValidTime()
                                .substring(0, timeSeries.getValidTime().length() - 1));
        Double temperature = parameters.get(1).getValues().get(0);
        Double windSpeed = parameters.get(4).getValues().get(0);

        return new SmhiDTO(dateTime, temperature, windSpeed);
    }
}
