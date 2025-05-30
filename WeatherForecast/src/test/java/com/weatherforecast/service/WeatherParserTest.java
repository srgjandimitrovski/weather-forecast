package com.weatherforecast.service;

import com.weatherforecast.model.Forecast;
import com.weatherforecast.service.WeatherParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherParserTest {

    private WeatherParser weatherParser;

    @BeforeEach
    public void setUp() {
        weatherParser = new WeatherParser();
    }

    @Test
    public void testParseForecast_WithHotAndRainyWeather() {
        String json = "{ \"list\": [ { \"dt\": 1626720000, \"temp\": { \"max\": 30.0 }, \"weather\": [ { \"main\": \"Clear\" } ] }, { \"dt\": 1626806400, \"temp\": { \"max\": 22.0 }, \"weather\": [ { \"main\": \"Rain\" } ] }, { \"dt\": 1626892800, \"temp\": { \"max\": 18.0 }, \"weather\": [ { \"main\": \"Clouds\" } ] } ] }";
        String city = "City1";

        List<Forecast> forecasts = weatherParser.parseForecast(json, city);

        assertEquals(2, forecasts.size());

        // Verify that forecast is hot
        Forecast hotForecast = forecasts.get(0);
        assertEquals("City1", hotForecast.getCity());
        assertEquals(30.0, hotForecast.getMaxTemp());
        assertEquals("Clear", hotForecast.getMain());

        // Verify forecast is rainy
        Forecast rainyForecast = forecasts.get(1);
        assertEquals("City1", rainyForecast.getCity());
        assertEquals(22.0, rainyForecast.getMaxTemp());
        assertEquals("Rain", rainyForecast.getMain());
    }

    @Test
    public void testParseForecast_LowTempNoRain() {
        String json = "{ \"list\": [ { \"dt\": 1626720000, \"temp\": { \"max\": 18.0 }, \"weather\": [ { \"main\": \"Clear\" } ] }, { \"dt\": 1626806400, \"temp\": { \"max\": 22.0 }, \"weather\": [ { \"main\": \"Clouds\" } ] } ] }";
        String city = "City2";

        List<Forecast> forecasts = weatherParser.parseForecast(json, city);

        assertTrue(forecasts.isEmpty());
    }

    @Test
    public void testParseForecast_RainyWeatherOnly() {
        String json = "{ \"list\": [ { \"dt\": 1626720000, \"temp\": { \"max\": 20.0 }, \"weather\": [ { \"main\": \"Rain\" } ] }, { \"dt\": 1626806400, \"temp\": { \"max\": 23.0 }, \"weather\": [ { \"main\": \"Rain\" } ] } ] }";
        String city = "City3";

        List<Forecast> forecasts = weatherParser.parseForecast(json, city);

        assertEquals(2, forecasts.size());

        assertEquals("Rain", forecasts.get(0).getMain());
        assertEquals("Rain", forecasts.get(1).getMain());
    }
}
