package com.maxeriksson.optimalholmen.controller;

import com.maxeriksson.optimalholmen.model.Coordinate;
import com.maxeriksson.optimalholmen.model.ForecastDTO;
import com.maxeriksson.optimalholmen.service.ForecastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forecast")
public class ForecastRestController {

    @Autowired private ForecastService service;

    @GetMapping
    public ResponseEntity<ForecastDTO> assumeBestForecast() {
        Coordinate coordinate = new Coordinate().setLongitude(18.0300).setLatitude(59.3110);
        int hoursAhead = 24;

        ForecastDTO forecast = service.assumeBestForecast(coordinate, hoursAhead);

        return ResponseEntity.ok().body(forecast);
    }
}
