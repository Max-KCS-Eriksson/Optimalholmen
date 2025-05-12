package com.maxeriksson.optimalholmen.client.openmeteo;

import com.maxeriksson.optimalholmen.client.openmeteo.model.OpenMeteoApiResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class OpenMeteoClient {

    WebClient webClient;

    public OpenMeteoClient() {
        String baseUrl = "https://api.open-meteo.com/v1/forecast";
        webClient = WebClient.create(baseUrl);
    }

    public Mono<OpenMeteoApiResponse> getForecast(double longitude, double latitude) {
        return webClient
                .get()
                .uri(
                        uriBuilder ->
                                uriBuilder
                                        .queryParam("longitude", longitude)
                                        .queryParam("latitude", latitude)
                                        // Requested values
                                        .queryParam("hourly", "temperature_2m")
                                        .queryParam("temperature_unit", "celsius")
                                        .queryParam("hourly", "wind_speed_10m")
                                        .queryParam("wind_speed_unit", "ms")
                                        .build())
                .retrieve()
                .bodyToMono(OpenMeteoApiResponse.class);
    }
}
