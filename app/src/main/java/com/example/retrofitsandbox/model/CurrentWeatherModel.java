package com.example.retrofitsandbox.model;

import com.example.retrofitsandbox.R;

public class CurrentWeatherModel {
    String cityName;
    double temperature;
    double feelsLike;
    String condition;
    String description;

    int iconId;

    public CurrentWeatherModel(String cityName, double temperature, double feelsLike, String condition, String description) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.condition = condition;
        this.description = description;
        this.iconId = getWeatherIconByCondition(condition);
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

    public String getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
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
