package com.maxeriksson.optimalholmen.client.smhi;

import com.maxeriksson.optimalholmen.client.smhi.model.SmhiApiResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class SmhiClient {

    WebClient webClient;

    public SmhiClient() {
        String baseUrl = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2";
        webClient = WebClient.create(baseUrl);
    }

    public Mono<SmhiApiResponse> getForecast(double longitude, double latitude) {
        return webClient
                .get()
                .uri(
                        uriBuilder ->
                                uriBuilder
                                        .path(
                                                "/geotype/point/lon/{longitude}/lat/{latitude}/data.json")
                                        .build(longitude, latitude))
                .retrieve()
                .bodyToMono(SmhiApiResponse.class);
    }
}
