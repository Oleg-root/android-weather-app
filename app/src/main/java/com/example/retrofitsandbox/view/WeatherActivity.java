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
import com.example.retrofitsandbox.model.CurrentWeatherModel;
import com.example.retrofitsandbox.presenter.WeatherPresenter;

import java.util.ArrayList;



public class WeatherActivity extends AppCompatActivity {
    private EditText editText_add;
    private WeatherPresenter presenter;
    private WM_RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        editText_add = findViewById(R.id.editText_Input);

        CityStorage.init(this);
        CityStorage.addDefaultProperties();

        presenter = new WeatherPresenter();
        presenter.attachView(this);

        RecyclerView citiesAndWeather = findViewById(R.id.recyclerView_weatherElement);
        adapter = new WM_RecyclerViewAdapter(this, presenter.currentWeatherModels);
        citiesAndWeather.setAdapter(adapter);
        citiesAndWeather.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.add(editText_add.getText().toString());
            }
        });
    }

    public void showToast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void showCitiesAndWeather(ArrayList<CurrentWeatherModel> currentWeatherModels) {
        adapter.setModels(currentWeatherModels);
        adapter.notifyDataSetChanged();
    }
}