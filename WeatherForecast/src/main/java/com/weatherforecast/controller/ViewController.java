package com.weatherforecast.controller;

import com.weatherforecast.model.Forecast;
import com.weatherforecast.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    private final WeatherService weatherService;



    public ViewController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather_hot")
    public String getWeather(Model model) {
        List<Forecast> allForecasts = weatherService.getHotForecasts();
        model.addAttribute("forecasts", allForecasts);
        return "weather_hot";
    }

    @GetMapping("/weather_rainy")
    public String getRainyWeather(Model model) {
        List<Forecast> allForecasts = weatherService.getRainyForecasts();
        model.addAttribute("forecasts", allForecasts);
        return "weather_rainy";
    }


}
