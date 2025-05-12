package com.maxeriksson.optimalholmen.controller;

import com.maxeriksson.optimalholmen.model.Coordinate;
import com.maxeriksson.optimalholmen.model.ForecastDTO;
import com.maxeriksson.optimalholmen.service.ForecastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/forecast")
public class ForecastController {

    @Autowired private ForecastService service;

    @GetMapping
    public ModelAndView assumeBestForecast(ModelMap model) {
        Coordinate coordinate = new Coordinate().setLongitude(18.0300).setLatitude(59.3110);
        int hoursAhead = 24;

        model.addAttribute("location", "Liljeholmen");

        ForecastDTO forecast = service.assumeBestForecast(coordinate, hoursAhead);
        model.addAttribute("forecast_source", forecast.forecastSource());
        model.addAttribute("date_time", forecast.dateTime());
        model.addAttribute("temperature", forecast.temperature());
        model.addAttribute("wind_speed", forecast.windSpeed());

        String viewName = "forecast";

        return new ModelAndView(viewName, model);
    }
}
