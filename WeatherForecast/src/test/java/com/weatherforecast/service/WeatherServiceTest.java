package com.weatherforecast.service;

import com.weatherforecast.model.Forecast;
import com.weatherforecast.repository.ForecastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    private ForecastRepository forecastRepository;
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        forecastRepository = mock(ForecastRepository.class);
        weatherService = new WeatherService(forecastRepository);
    }

    @Test
    void testGetHotForecasts() {
        Forecast f1 = new Forecast();
        f1.setCity("City1");
        f1.setMaxTemp(25.00);
        f1.setDate(LocalDate.now());
        f1.setMain("Clear");

        when(forecastRepository.findByMaxTempGreaterThan(25.00)).thenReturn(List.of(f1));

        List<Forecast> result = weatherService.getHotForecasts();
        assertEquals(1, result.size());
        assertEquals("City1", result.get(0).getCity());
        verify(forecastRepository, times(1)).findByMaxTempGreaterThan(25.00);
    }

    @Test
    void testGetRainyForecasts() {
        Forecast f1 = new Forecast();
        f1.setCity("City2");
        f1.setMaxTemp(20.0);
        f1.setDate(LocalDate.now());
        f1.setMain("Rain");

        when(forecastRepository.findByMain("Rain")).thenReturn(List.of(f1));

        List<Forecast> result = weatherService.getRainyForecasts();
        assertEquals(1, result.size());
        assertEquals("Rain", result.get(0).getMain());
        verify(forecastRepository, times(1)).findByMain("Rain");
    }
}

