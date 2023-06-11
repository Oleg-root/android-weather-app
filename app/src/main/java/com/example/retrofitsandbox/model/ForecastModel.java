package com.example.retrofitsandbox.model;

import com.example.retrofitsandbox.R;

public class ForecastModel {

    String datetime;
    double temperature;
    String condition;
    int iconId;

    public ForecastModel(String datetime, double temperature, String condition) {
        this.datetime = datetime;
        this.temperature = temperature;
        this.condition = condition;
        this.iconId = getWeatherIconByCondition(condition);
    }

    public String getDatetime() {
        return datetime;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }

    public int getIconId() {
        return iconId;
    }

    private int getWeatherIconByCondition(String condition) {
        switch (condition) {
            case "Clear":
                return R.drawable.ic_sunny;
            case "Clouds":
                return R.drawable.ic_cloudy;
            case "Rain":
                return R.drawable.ic_rainy;
            case "Thunderstorm":
                return R.drawable.ic_rainythunder;
            case "Snow":
                return R.drawable.ic_snowy;
            case "Drizzle":
                return R.drawable.ic_rainshower;
            case "Mist":
                return R.drawable.ic_windy;
            default:
                return R.drawable.ic_sunnycloudy;

        }
    }
}
