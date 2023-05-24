package com.example.retrofitsandbox.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitsandbox.R;
import com.example.retrofitsandbox.model.CityStorage;
import com.example.retrofitsandbox.model.CurrentWeatherData;
import com.example.retrofitsandbox.model.WeatherModel;
import com.example.retrofitsandbox.service.WeatherApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
    private EditText editText_add;
    private Button button_add;
    ArrayList<WeatherModel> weatherModels = new ArrayList<>(); // gonna hold all the models and then we're gonna send them to recyclerview adapter
    WM_RecyclerViewAdapter adapter;
    ArrayList<String> cityNames;
    WeatherApi weatherApi;

    private void setUpWeatherModels(WeatherApi weatherApi) {
        for (int i = 0; i < cityNames.size(); i++) {
            getWeatherDataAndPutItInWeatherModels(weatherApi, cityNames.get(i));
        }
    }
    private WeatherApi initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        return weatherApi;
    }

    private void getWeatherDataAndPutItInWeatherModels(WeatherApi weatherApi, String city) {

        Call<CurrentWeatherData> call = weatherApi.getAllData(city);
        call.enqueue(new Callback<CurrentWeatherData>() {
            @Override
            public void onResponse(Call<CurrentWeatherData> call, Response<CurrentWeatherData> response) {
                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code:" + response.code());
                    Log.d("getWeatherData", "Something went wrong");
                    Toast.makeText(WeatherActivity.this, "Wrong city. Try a different one.", Toast.LENGTH_SHORT).show();
                    CityStorage.pop(city);
                    return;
                } else {
                    CurrentWeatherData currentWeatherData = response.body();
                    WeatherModel weatherModel = new WeatherModel(city, currentWeatherData.getMain().getTemp(), currentWeatherData.getMain().getFeelsLike());
                    weatherModels.add(weatherModel);
                    adapter.setModels(weatherModels);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                Log.d("getWeatherData onFailure", "Something went wrong", t);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View
        editText_add = findViewById(R.id.editText_Input);
        button_add = findViewById(R.id.button_add);

        // sharedPreference
        CityStorage.init(this);
        CityStorage.addProperty("Moscow", "Moscow");
        CityStorage.addProperty("Saint Petersburg", "Saint Petersburg");
        cityNames = CityStorage.getAllProperty();

        // апи
        weatherApi = initRetrofit();

        RecyclerView recyclerView = findViewById(R.id.recyclerView_weatherElement);

        setUpWeatherModels(weatherApi);

        adapter = new WM_RecyclerViewAdapter(this, weatherModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        View.OnClickListener oclButton_add = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cityNames.contains(editText_add.getText().toString())) {
                    CityStorage.addProperty(editText_add.getText().toString(), editText_add.getText().toString());
                    cityNames = CityStorage.getAllProperty();
                    getWeatherDataAndPutItInWeatherModels(weatherApi, editText_add.getText().toString());
                } else {
                    Toast.makeText(WeatherActivity.this, "This city already exists.", Toast.LENGTH_SHORT).show();
                    // TODO: Show "This city already exists." to the user
                }


            }
        };
        button_add.setOnClickListener(oclButton_add);

    }




}