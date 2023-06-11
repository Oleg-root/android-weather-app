package com.example.retrofitsandbox.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastElements {

    private ForecastMain main;

    private List<ForecastWeather> weather;

    @SerializedName("dt_txt")
    String datetime;

    public ForecastElements(ForecastMain main, List<ForecastWeather> weather, String datetime) {
        this.main = main;
        this.weather = weather;
        this.datetime = datetime;
    }

    public ForecastMain getMain() {
        return main;
    }

    public List<ForecastWeather> getWeather() {
        return weather;
    }

    public String getDatetime() {
        return datetime;
    }
}
