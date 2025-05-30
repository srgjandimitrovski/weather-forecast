package com.weatherforecast.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private LocalDate date;
    private double maxTemp;
    private String main;

    public String getMain() {
        return main;
    }

    public double getMaxTemp() {
        return maxTemp;
    }


    public LocalDate getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
