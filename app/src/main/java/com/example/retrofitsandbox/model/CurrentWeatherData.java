package com.example.retrofitsandbox.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherData {

    @SerializedName("weather")
    private List<CurrentWeatherCondition> list;
    private CurrentMain main;
    private ForecastWindData wind;

    public CurrentWeatherData(CurrentMain main, List<CurrentWeatherCondition> list, ForecastWindData wind) {
        this.main = main;
        this.list = list;
        this.wind = wind;

    }

    public CurrentMain getMain() {
        return main;
    }

    public List<CurrentWeatherCondition> getList() {
        return list;
    }

    public ForecastWindData getWind() {
        return wind;
    }




}
