package com.example.retrofitsandbox.model;

import com.google.gson.annotations.SerializedName;

public class ForecastWeather {
    @SerializedName("main")
    private String condition;

    public ForecastWeather(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
