package com.example.retrofitsandbox.model;

import com.google.gson.annotations.SerializedName;

public class ForecastMain {

    @SerializedName("temp")
    private double temperature;

    public ForecastMain(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}
