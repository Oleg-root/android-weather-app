package com.example.retrofitsandbox.model;

import com.google.gson.annotations.SerializedName;

public class MainData {
    private double temp;
    @SerializedName("feels_like")
    private double feelsLike;
    @SerializedName("temp_min")
    private double tempMin;
    @SerializedName("temp_max")
    private double tempMax;
    private int pressure;
    private int humidity;

    public MainData(double temp, double feelsLike, double tempMin, double tempMax, int pressure, int humidity) {
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
    }


    public double getTemp() {
        return temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public int getPressure() {
        return pressure;
    }

//    public int getSeaLevel() {
//        return seaLevel;
//    }

//    public int getGrndLevel() {
//        return grndLevel;
//    }

    public int getHumidity() {
        return humidity;
    }

//    public double getTempKf() {
//        return tempKf;
//    }
}
