package com.maxeriksson.optimalholmen.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.maxeriksson.optimalholmen.client.met.MetClient;
import com.maxeriksson.optimalholmen.client.met.mapper.MetMapper;
import com.maxeriksson.optimalholmen.client.smhi.SmhiClient;
import com.maxeriksson.optimalholmen.client.smhi.mapper.SmhiMapper;
import com.maxeriksson.optimalholmen.model.Coordinate;
import com.maxeriksson.optimalholmen.model.ForecastDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ForecastServiceTest {

    @Mock private MetClient metClient;
    @Mock private MetMapper metMapper;

    @Mock private SmhiClient smhiClient;
    @Mock private SmhiMapper smhiMapper;

    @InjectMocks private static ForecastService service;

    // Set up

    private static List<ForecastDTO> forecasts;
    private static LocalDateTime dateTime = LocalDateTime.now();
    ;
    private static double longitude = 18.0300;
    private static double latitude = 59.3110;
    private static Coordinate coordinate =
            new Coordinate().setLongitude(longitude).setLatitude(latitude);
    private static int hoursAhead = 24;

    @BeforeEach
    private void setUp() {
        forecasts = new ArrayList<>();
    }

    // Unit tests

    @Test
    public void testForecastTemperaturePreference() {
        forecasts.add(new ForecastDTO("a", dateTime, 1.5, 5));
        forecasts.add(new ForecastDTO("b", dateTime, 2, 5));

        ForecastDTO expected = forecasts.getLast();
        ForecastDTO actual = service.assumeBestForecast(coordinate, hoursAhead, forecasts);

        assertEquals(expected, actual);
    }

    @Test
    public void testForecastSecondlyWindSpeedPreference() {
        forecasts.add(new ForecastDTO("a", dateTime, 2, 5));
        forecasts.add(new ForecastDTO("b", dateTime, 2, 1));

        ForecastDTO expected = forecasts.getLast();
        ForecastDTO actual = service.assumeBestForecast(coordinate, hoursAhead, forecasts);

        assertEquals(expected, actual);
    }
}
