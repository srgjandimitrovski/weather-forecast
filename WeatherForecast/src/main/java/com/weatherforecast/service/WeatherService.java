package com.weatherforecast.service;

import com.weatherforecast.model.City;
import com.weatherforecast.model.Forecast;
import com.weatherforecast.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;

import java.util.List;

@Service
public class WeatherService {

    private final ForecastRepository forecastRepository;

    @Value("${weather.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create("https://api.openweathermap.org");

    public WeatherService(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @PostConstruct
    public void init() {
        for (City city : City.values()) {
            fetchAndSaveForecasts(city);
        }
    }

    public void fetchAndSaveForecasts(City city) {
        String url = String.format("/data/2.5/forecast/daily?lat=%f&lon=%f&cnt=16&units=metric&appid=%s",
                city.lat, city.lon, apiKey);

        webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(json -> WeatherParser.parseForecast(json, city.name()))
                .doOnNext(forecastRepository::saveAll)
                .subscribe();
    }

    public List<Forecast> getForecasts(String city) {
        return forecastRepository.findByCity(city.toUpperCase());
    }

    public List<Forecast> getHotForecasts() {
        List<Forecast> hotForecasts = forecastRepository.findByMaxTempGreaterThan(25.00);
        if (hotForecasts == null || hotForecasts.isEmpty()) {
            throw new ForecastNotFoundException("No hot forecasts found (maxTemp > 25°C).");
        }
        return hotForecasts;    }

    public List<Forecast> getRainyForecasts() {
        List<Forecast> rainyForecasts = forecastRepository.findByMain("Rain");
        if (rainyForecasts == null || rainyForecasts.isEmpty()) {
            throw new ForecastNotFoundException("No rainy forecasts found.");
        }
        return rainyForecasts;    
    }
}
