package com.example.retrofitsandbox.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.retrofitsandbox.R;
import com.example.retrofitsandbox.model.CityStorage;
import com.example.retrofitsandbox.model.CurrentWeatherData;
import com.example.retrofitsandbox.model.WeatherModel;
import com.example.retrofitsandbox.service.WeatherApi;
import com.example.retrofitsandbox.view.WeatherActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherPresenter {
    private WeatherActivity view;
    private WeatherModel weatherModel;

    public void attachView(WeatherActivity weatherActivity) {
        view = weatherActivity;
    }

    public void detachView() {
        view = null;
    }

    public WeatherApi initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(WeatherApi.class);
    }

    public void add() {
        String enteredCity = view.getEnteredCity();
        ArrayList<String> cityNames = CityStorage.getAllProperty();
        if (!cityNames.contains(enteredCity)) {
            CityStorage.addProperty(enteredCity, enteredCity);
            getWeatherDataAndPutItInWeatherModels(view.getWeatherApi(), enteredCity);
        } else {
            view.showToast(R.string.toast_city_already_exists);
        }
    }

    public void setUpWeatherModels(WeatherApi weatherApi) {
        for (int i = 0; i < CityStorage.getAllProperty().size(); i++) {
            getWeatherDataAndPutItInWeatherModels(weatherApi, CityStorage.getAllProperty().get(i));
        }
    }

    private void getWeatherDataAndPutItInWeatherModels(WeatherApi weatherApi, String city) {

        Call<CurrentWeatherData> call = weatherApi.getAllData(city);
        call.enqueue(new Callback<CurrentWeatherData>() {
            @Override
            public void onResponse(Call<CurrentWeatherData> call, Response<CurrentWeatherData> response) {
                if (!response.isSuccessful()) {
                    Log.d("getWeatherData onResponse", "Something went wrong");
                    view.showToast(R.string.toast_city_does_not_exist);
                    CityStorage.pop(city);
                } else {
                    CurrentWeatherData currentWeatherData = response.body();
                    weatherModel = new WeatherModel(city, currentWeatherData.getMain().getTemp(), currentWeatherData.getMain().getFeelsLike());
                    view.getWeatherModels().add(weatherModel);
                    view.showCitiesAndWeather(view.getWeatherModels());
                }
            }
            @Override
            public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
                Log.d("getWeatherData onFailure", "Something went wrong", t);
            }
        });
    }
}
