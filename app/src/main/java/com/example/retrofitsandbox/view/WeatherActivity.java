package com.example.retrofitsandbox.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitsandbox.R;
import com.example.retrofitsandbox.model.CityStorage;
import com.example.retrofitsandbox.model.WeatherModel;
import com.example.retrofitsandbox.presenter.WeatherPresenter;
import com.example.retrofitsandbox.service.WeatherApi;

import java.util.ArrayList;



public class WeatherActivity extends AppCompatActivity {
    private EditText editText_add;
    ArrayList<WeatherModel> weatherModels = new ArrayList<>(); // gonna hold all the models and then we're gonna send them to recyclerview adapter
    WM_RecyclerViewAdapter adapter;
    WeatherPresenter presenter;
    //ArrayList<String> cityNames;
    WeatherApi weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        editText_add = findViewById(R.id.editText_Input);

        presenter = new WeatherPresenter();
        presenter.attachView(this);

        CityStorage.init(this);
        CityStorage.addDefaultProperties();

        weatherApi = presenter.initRetrofit();

        presenter.setUpWeatherModels(weatherApi);

        RecyclerView citiesAndWeather = findViewById(R.id.recyclerView_weatherElement);
        adapter = new WM_RecyclerViewAdapter(this, weatherModels);
        citiesAndWeather.setAdapter(adapter);
        citiesAndWeather.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.add();
            }
        });
    }



    public void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public String getEnteredCity() {
        return editText_add.getText().toString();
    }

    public WeatherApi getWeatherApi() {
        return weatherApi;
    }

    public void showCitiesAndWeather(ArrayList<WeatherModel> weatherModels) {
        adapter.setModels(weatherModels);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<WeatherModel> getWeatherModels() {
        return weatherModels;
    }
}