package com.example.retrofitsandbox.model;

import com.google.gson.annotations.SerializedName;

public class CurrentWeatherCondition {
    @SerializedName("main")
    private String condition;
    private String description;
    private int iconId;

    public CurrentWeatherCondition(String condition, String description, int iconId) {
        this.condition = condition;
        this.description = description;
        this.iconId = iconId;
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
}
