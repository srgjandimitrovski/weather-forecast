package com.weatherforecast.model;

public enum City {
    SKOPJE(41.9981, 21.4254),
    OHRID(41.1231, 20.8016),
    BITOLA(41.0300, 21.3400);

    public final double lat;
    public final double lon;

    City(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
