package com.example.retrofitsandbox.model;

public class CurrentWeatherData {
    private MainData main;

    public CurrentWeatherData(MainData main) {
        this.main = main;
    }

    public MainData getMain() {
        return main;
    }
}
