package com.example.retrofitsandbox.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitsandbox.R;
import com.example.retrofitsandbox.model.CityStorage;
import com.example.retrofitsandbox.model.CurrentWeatherModel;
import com.example.retrofitsandbox.model.ForecastModel;
import com.example.retrofitsandbox.presenter.WeatherPresenter;

import java.util.ArrayList;
import java.util.List;


public class WeatherActivity extends AppCompatActivity {
    private WeatherPresenter presenter;
    private HorizontalRV horizontalRVAdapter;
    public TextView city_name, current_temperature, description, humidity, pressure, wind;
    public ImageView current_weather_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        init();
    }

    private void init() {

        city_name = findViewById(R.id.tv_cityName);
        current_temperature = findViewById(R.id.tv_temperature);
        description = findViewById(R.id.tv_description);
        humidity = findViewById(R.id.tv_humidity);
        pressure = findViewById(R.id.tv_pressure);
        wind = findViewById(R.id.tv_wind);
        current_weather_icon = findViewById(R.id.iv_weatherIcon);

        CityStorage.init(this);
        CityStorage.addDefaultProperties();

        presenter = new WeatherPresenter();
        presenter.attachView(this);

        RecyclerView horizontalrecview = findViewById(R.id.recyclerView_new);
        horizontalRVAdapter = new HorizontalRV(this, presenter.forecastModels);
        horizontalrecview.setAdapter(horizontalRVAdapter);
        horizontalrecview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void showrecyclerview(ArrayList<ForecastModel> forecastModels) {
        horizontalRVAdapter.setModels(forecastModels);
        horizontalRVAdapter.notifyDataSetChanged();
    }

    public void showCurrentWeather(CurrentWeatherModel currentWeatherModel) {
        city_name.setText(currentWeatherModel.getCityName());
        current_temperature.setText(String.format("%.1f °C", currentWeatherModel.getTemperature()));
        description.setText(currentWeatherModel.getDescription());
        humidity.setText(String.format("%d%%", currentWeatherModel.getHumidity()));
        pressure.setText(String.format("%d mb", currentWeatherModel.getPressure()));
        wind.setText(String.format("%.2f km/h", currentWeatherModel.getWindSpeed()));
        current_weather_icon.setImageResource(currentWeatherModel.getIconId());
    }
}