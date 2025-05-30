package com.weatherforecast.repository;

import com.weatherforecast.model.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    List<Forecast> findByCity(String city);
    List<Forecast> findByMaxTempGreaterThan(Double maxTemp);


    List<Forecast> findByMain(String rain);
}
