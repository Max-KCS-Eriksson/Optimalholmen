package com.maxeriksson.optimalholmen.client.met;

import com.maxeriksson.optimalholmen.client.met.model.MetApiResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class MetClient {

    WebClient webClient;

    public MetClient() {
        String baseUrl = "https://api.met.no/weatherapi/locationforecast/2.0/compact";
        webClient = WebClient.create(baseUrl);
    }

    public Mono<MetApiResponse> getForecast(double longitude, double latitude) {
        return webClient
                .get()
                .uri(
                        uriBuilder ->
                                uriBuilder
                                        .replaceQueryParam("lon", longitude)
                                        .replaceQueryParam("lat", latitude)
                                        .build())
                .retrieve()
                .bodyToMono(MetApiResponse.class);
    }
}
