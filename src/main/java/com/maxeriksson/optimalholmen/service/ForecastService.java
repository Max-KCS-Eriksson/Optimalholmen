package com.maxeriksson.optimalholmen.service;

import com.maxeriksson.optimalholmen.client.met.MetClient;
import com.maxeriksson.optimalholmen.client.met.mapper.MetMapper;
import com.maxeriksson.optimalholmen.client.met.model.MetApiResponse;
import com.maxeriksson.optimalholmen.client.openmeteo.OpenMeteoClient;
import com.maxeriksson.optimalholmen.client.openmeteo.mapper.OpenMeteoMapper;
import com.maxeriksson.optimalholmen.client.openmeteo.model.OpenMeteoApiResponse;
import com.maxeriksson.optimalholmen.client.smhi.SmhiClient;
import com.maxeriksson.optimalholmen.client.smhi.mapper.SmhiMapper;
import com.maxeriksson.optimalholmen.client.smhi.model.SmhiApiResponse;
import com.maxeriksson.optimalholmen.model.Coordinate;
import com.maxeriksson.optimalholmen.model.ForecastDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ForecastService {

    @Autowired private MetClient metClient;
    @Autowired private MetMapper metMapper;

    @Autowired private SmhiClient smhiClient;
    @Autowired private SmhiMapper smhiMapper;

    @Autowired private OpenMeteoClient openMeteoClient;
    @Autowired private OpenMeteoMapper openMeteoMapper;

    /**
     * Assumes the preferred weather forecast, prioritizing warm weather, and secondly weaker wind.
     */
    public ForecastDTO assumeBestForecast(Coordinate coordinate, int hoursAhead) {
        List<ForecastDTO> forecasts = getForecasts(coordinate, hoursAhead);
        return assumeBestForecast(coordinate, hoursAhead, forecasts);
    }

    /** Use for testing only */
    ForecastDTO assumeBestForecast(
            Coordinate coordinate, int hoursAhead, List<ForecastDTO> forecasts) {
        ForecastDTO bestForecast = forecasts.getFirst();
        for (ForecastDTO forecast : forecasts.subList(1, forecasts.size())) {
            if (forecast.temperature() > bestForecast.temperature()) {
                bestForecast = forecast;
            } else if (forecast.temperature() == bestForecast.temperature()
                    && forecast.windSpeed() < bestForecast.windSpeed()) {
                bestForecast = forecast;
            }
        }

        return bestForecast;
    }

    public List<ForecastDTO> getForecasts(Coordinate coordinate, int hoursAhead) {
        double longitude = coordinate.getLongitude();
        double latitude = coordinate.getLatitude();

        CompletableFuture<MetApiResponse> metRequest =
                metClient.getForecast(longitude, latitude).toFuture();
        CompletableFuture<SmhiApiResponse> smhiRequest =
                smhiClient.getForecast(longitude, latitude).toFuture();
        CompletableFuture<OpenMeteoApiResponse> openMeteoRequest =
                openMeteoClient.getForecast(longitude, latitude).toFuture();

        List<ForecastDTO> forecasts = new ArrayList<>();

        Optional<MetApiResponse> metResponse = completeFuture(metRequest);
        if (metResponse.isPresent())
            forecasts.add(metMapper.toForecastDTO(metResponse.get(), hoursAhead));
        Optional<SmhiApiResponse> smhiResponse = completeFuture(smhiRequest);
        if (smhiResponse.isPresent())
            forecasts.add(smhiMapper.toForecastDTO(smhiResponse.get(), hoursAhead));
        Optional<OpenMeteoApiResponse> openMeteoResponse = completeFuture(openMeteoRequest);
        if (openMeteoResponse.isPresent())
            forecasts.add(openMeteoMapper.toForecastDTO(openMeteoResponse.get(), hoursAhead));

        return forecasts;
    }

    private <T> Optional<T> completeFuture(CompletableFuture<T> future) {
        try {
            return Optional.of(future.get());
        } catch (InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
    }
}
