package com.example.retrofitsandbox.service;

import com.example.retrofitsandbox.model.CurrentWeatherData;
import com.example.retrofitsandbox.model.ForecastData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather?appid=4834f8823e9ad5ae04d03194f029e462&units=metric")
    Call<CurrentWeatherData> getCurrentData(@Query("q") String city);

    @GET("data/2.5/forecast?appid=b81050bfe2211f506b9141d428870593&units=metric")
    Call<ForecastData> getForecastData(@Query("q") String city);
}
