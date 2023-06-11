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
import com.example.retrofitsandbox.model.ForecastModel;

import java.util.ArrayList;

public class HorizontalRV extends RecyclerView.Adapter<HorizontalRV.MyViewHolder> {

    Context context;

    ArrayList<ForecastModel> forecastModels;

    public HorizontalRV(Context context, ArrayList<ForecastModel> forecastModels) {
        this.context = context;
        this.forecastModels = forecastModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row_new, parent, false);
        return new HorizontalRV.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.temperature.setText(String.format("%.1f °C", forecastModels.get(position).getTemperature()));
        holder.date.setText(String.format(forecastModels.get(position).getDatetime()).substring(11, 16));
        holder.weatherIcon.setImageResource(forecastModels.get(position).getIconId());
    }

    @Override
    public int getItemCount() {
        return forecastModels.size();
    }

    public void setModels(ArrayList<ForecastModel> models) {
        forecastModels = models;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, temperature;
        ImageView weatherIcon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tv_date);
            temperature = itemView.findViewById(R.id.tv_temperature_forecast);
            weatherIcon = itemView.findViewById(R.id.iv_forecastWeatherIcon);
        }
    }
}
