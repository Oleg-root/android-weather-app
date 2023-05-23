package com.example.retrofitsandbox.service;

import com.example.retrofitsandbox.model.CurrentWeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather?appid=4834f8823e9ad5ae04d03194f029e462&units=metric")
    Call<CurrentWeatherData> getAllData(@Query("q") String city);
}
