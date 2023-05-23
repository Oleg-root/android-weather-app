package com.example.retrofitsandbox.model;

public class WeatherModel {
    String cityName;
    double temperature;
    double feelsLike;

    public WeatherModel(String cityName, double temperature, double feelsLike) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }
}
