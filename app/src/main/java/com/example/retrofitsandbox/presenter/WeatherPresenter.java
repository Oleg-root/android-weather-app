package com.example.retrofitsandbox.presenter;

import android.util.Log;

import com.example.retrofitsandbox.R;
import com.example.retrofitsandbox.model.CityStorage;
import com.example.retrofitsandbox.model.CurrentWeatherData;
import com.example.retrofitsandbox.model.CurrentWeatherModel;
import com.example.retrofitsandbox.model.ForecastData;
import com.example.retrofitsandbox.model.ForecastModel;
import com.example.retrofitsandbox.service.WeatherApi;
import com.example.retrofitsandbox.view.WeatherActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherPresenter {
    // gonna hold all the models and then we're gonna send them to recyclerview adapter
    public ArrayList<CurrentWeatherModel> currentWeatherModels = new ArrayList<>();
    private final WeatherApi weatherApi;
    private WeatherActivity view;
    private CurrentWeatherModel currentWeatherModel;
    private ForecastModel forecastModel;
    public ArrayList<ForecastModel> forecastModels = new ArrayList<>();

    public WeatherPresenter() {
        weatherApi = initRetrofit();
        setUpWeatherModels(weatherApi);
        getWeatherDataAndPutItInWeatherModels(weatherApi, CityStorage.getProperty("Moscow"));
    }

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

    public void add(String enteredCity) {
        ArrayList<String> cityNames = CityStorage.getAllProperty();
        if (!cityNames.contains(enteredCity)) {
            CityStorage.addProperty(enteredCity, enteredCity);
            getWeatherDataAndPutItInWeatherModels(weatherApi, enteredCity);
        } else {
            view.showToast(R.string.toast_city_already_exists);
        }
    }

    public void setUpWeatherModels(WeatherApi weatherApi) {
        for (int i = 0; i < CityStorage.getAllProperty().size(); i++) {
            getForecastDataAndPutItInWeatherModels(weatherApi, CityStorage.getAllProperty().get(i));
        }
    }

    // Current weather data
    private void getWeatherDataAndPutItInWeatherModels(WeatherApi weatherApi, String city) {
        Call<CurrentWeatherData> call = weatherApi.getCurrentData(city);
        call.enqueue(new Callback<CurrentWeatherData>() {
            @Override
            public void onResponse(Call<CurrentWeatherData> call, Response<CurrentWeatherData> response) {
                if (!response.isSuccessful()) {
                    Log.d("getWeatherData onResponse", "Something went wrong");
                } else {
                    CurrentWeatherData currentWeatherData = response.body();
                    currentWeatherModel = new CurrentWeatherModel(city, currentWeatherData.getMain().getTemp(),
                                                                        currentWeatherData.getMain().getFeelsLike(),
                                                                        currentWeatherData.getList().get(0).getCondition(),
                                                                        currentWeatherData.getList().get(0).getDescription(),
                                                                        currentWeatherData.getMain().getHumidity(),
                                                                        currentWeatherData.getMain().getPressure(),
                                                                        currentWeatherData.getWind().getSpeed());
                    currentWeatherModels.add(currentWeatherModel);
                    view.showCurrentWeather(currentWeatherModel);
                }
            }
            @Override
            public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
                Log.d("getWeatherData onFailure", "Something went wrong", t);
            }
        });
    }

    // Forecast for every 3 hours
    private void getForecastDataAndPutItInWeatherModels(WeatherApi weatherApi, String city) {
        Call<ForecastData> call = weatherApi.getForecastData(city);
        call.enqueue(new Callback<ForecastData>() {
            @Override
            public void onResponse(Call<ForecastData> call, Response<ForecastData> response) {
                if (!response.isSuccessful()) {
                    Log.d("getForecastData onResponse", "Something went wrong");
                } else {
                    ForecastData forecastData = response.body();
                    for (int i = 0; i < forecastData.getList().size(); i++) {
                        forecastModel = new ForecastModel(forecastData.getList().get(i).getDatetime(),
                                forecastData.getList().get(i).getMain().getTemperature(),
                                forecastData.getList().get(i).getWeather().get(0).getCondition());
                        forecastModels.add(forecastModel);
                    }
                    view.showrecyclerview(forecastModels);
                }
            }
            @Override
            public void onFailure(Call<ForecastData> call, Throwable t) {
                Log.d("getForecastData onFailure", "Something went wrong", t);
            }
        });
    }
}
