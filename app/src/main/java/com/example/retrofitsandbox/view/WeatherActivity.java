package com.example.retrofitsandbox.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.retrofitsandbox.R;
import com.example.retrofitsandbox.model.CityStorage;
import com.example.retrofitsandbox.model.CurrentWeatherData;
import com.example.retrofitsandbox.model.WeatherModel;
import com.example.retrofitsandbox.service.WeatherApi;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
    private TextView textViewResult;
    ArrayList<WeatherModel> weatherModels = new ArrayList<>(); // gonna hold all the models and then we're gonna send them to recyclerview adapter

    ArrayList<String> cityNames;

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
                    return;
                }
                CurrentWeatherData currentWeatherData = response.body();

                WeatherModel weatherModel = new WeatherModel(city, currentWeatherData.getMain().getTemp(), currentWeatherData.getMain().getFeelsLike());
                weatherModels.add(weatherModel);
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
        //textViewResult = findViewById(R.id.textView_result);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_weatherElement);

        // sharedPreference
        CityStorage.init(this);
        CityStorage.addProperty("Moscow", "Moscow");
        CityStorage.addProperty("Saint Petersburg", "Saint Petersburg");
        cityNames = CityStorage.getAllProperty();

        // апи
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // TODO: CRASH HERE
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        //WeatherApi weatherApi = initRetrofit();

        // модели для отображения на экране
        setUpWeatherModels(weatherApi);

        WM_RecyclerViewAdapter adapter = new WM_RecyclerViewAdapter(this, weatherModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





//        Call<CurrentWeatherData> call = weatherApi.getAllData("Moscow");
//        call.enqueue(new Callback<CurrentWeatherData>() {
//            @Override
//            public void onResponse(Call<CurrentWeatherData> call, Response<CurrentWeatherData> response) {
//                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code:" + response.code());
//                    return;
//                }
//                CurrentWeatherData currentWeatherData = response.body();
//                String result = String.valueOf(currentWeatherData.getMain().getTemp());
//                textViewResult.append(result);
//            }
//            @Override
//            public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });

    }


}