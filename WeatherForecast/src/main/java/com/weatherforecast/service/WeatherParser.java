package com.weatherforecast.service;

import com.weatherforecast.model.Forecast;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class WeatherParser {

    public static List<Forecast> parseForecast(String json, String city) {
        List<Forecast> forecasts = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode list = root.path("list");

            for (JsonNode day : list) {
                Forecast f = new Forecast();
                f.setCity(city);

                long dt = day.get("dt").asLong();
                f.setDate(LocalDate.ofInstant(Instant.ofEpochSecond(dt), ZoneId.systemDefault()));

                JsonNode temp = day.get("temp");
                double maxTemp = temp.get("max").asDouble();
                f.setMaxTemp(temp.get("max").asDouble());

                f.setMain(day.get("weather").get(0).get("main").asText());
                if(maxTemp>25 || day.get("weather").get(0).get("main").asText().equals("Rain"))
                forecasts.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return forecasts;
    }
}
