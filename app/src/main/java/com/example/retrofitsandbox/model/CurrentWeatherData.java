package com.example.retrofitsandbox.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherData {

    @SerializedName("weather")
    private List<CurrentWeatherCondition> list;
    private MainData main;

    public CurrentWeatherData(MainData main, List<CurrentWeatherCondition> list) {
        this.main = main;
        this.list = list;

    }

    public MainData getMain() {
        return main;
    }

    public List<CurrentWeatherCondition> getList() {
        return list;
    }




}
