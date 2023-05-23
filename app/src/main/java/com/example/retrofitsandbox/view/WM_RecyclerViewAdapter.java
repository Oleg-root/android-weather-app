package com.example.retrofitsandbox.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitsandbox.R;
import com.example.retrofitsandbox.model.WeatherModel;

import java.util.ArrayList;

public class WM_RecyclerViewAdapter extends RecyclerView.Adapter<WM_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<WeatherModel> weatherModels;

    public WM_RecyclerViewAdapter(Context context, ArrayList<WeatherModel> weatherModels) {
        this.context = context;
        this.weatherModels = weatherModels;
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

        holder.cityName.setText(weatherModels.get(position).getCityName());
        holder.temperature.setText((int) weatherModels.get(position).getTemperature());
        holder.feelsLike.setText((int) weatherModels.get(position).getFeelsLike());
    }

    @Override
    public int getItemCount() {
        // total items

        return weatherModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Grab the views from recyclerview_row.xml

        TextView cityName, temperature, feelsLike;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cityName = itemView.findViewById(R.id.textView);
            temperature = itemView.findViewById(R.id.textView2);
            feelsLike = itemView.findViewById(R.id.textView4);
        }
    }
}
