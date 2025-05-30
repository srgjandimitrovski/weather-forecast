package com.weatherforecast.controller;

import com.weatherforecast.model.City;
import com.weatherforecast.model.Forecast;
import com.weatherforecast.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public List<Forecast> getForecast(@PathVariable String city) {

        return weatherService.getForecasts(city);
    }

    @GetMapping("/get_hot")
    public List<Forecast> getHotForecast() {
        return weatherService.getHotForecasts();
    }
    @GetMapping("/get_rainy")
    public List<Forecast> getRainyForecast() {
        return weatherService.getRainyForecasts();
    }
}
