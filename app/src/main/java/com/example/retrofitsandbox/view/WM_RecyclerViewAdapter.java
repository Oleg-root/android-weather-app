package com.example.retrofitsandbox.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitsandbox.R;
import com.example.retrofitsandbox.model.CurrentWeatherModel;
import com.example.retrofitsandbox.presenter.WeatherPresenter;

import java.util.ArrayList;

public class WM_RecyclerViewAdapter extends RecyclerView.Adapter<WM_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<CurrentWeatherModel> currentWeatherModels;

    public WM_RecyclerViewAdapter(Context context, ArrayList<CurrentWeatherModel> currentWeatherModels) {
        this.context = context;
        this.currentWeatherModels = currentWeatherModels;
    }

    @NonNull
    @Override
    public WM_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout (Give a look to rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new WM_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WM_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // Assign values to the views created in recyclerview_row.xml
        // based on the position of the RecyclerView
        holder.cityName.setText(currentWeatherModels.get(position).getCityName());
        holder.temperature.setText(String.format("%.1f °C", currentWeatherModels.get(position).getTemperature()));
        holder.feelsLike.setText(String.format("%.1f °C", currentWeatherModels.get(position).getFeelsLike()));
        holder.weather.setImageResource(currentWeatherModels.get(position).getIconId());

    }

    @Override
    public int getItemCount() {
        // total items
        return currentWeatherModels.size();
    }

    public void setModels(ArrayList<CurrentWeatherModel> models) {
        currentWeatherModels = models;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Grab the views from recyclerview_row.xml
        TextView cityName, temperature, feelsLike;
        ImageView weather;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.textView);
            temperature = itemView.findViewById(R.id.textView2);
            feelsLike = itemView.findViewById(R.id.textView4);
            weather = itemView.findViewById(R.id.iv_weather);
        }
    }
}
