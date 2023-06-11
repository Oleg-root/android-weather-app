package com.example.retrofitsandbox.model;

import java.util.List;

public class ForecastData {

    private List<ForecastElements> list;

    public ForecastData (List<ForecastElements> list) {
        this.list = list;
    }
    public List<ForecastElements> getList() {
        return list;
    }
}
