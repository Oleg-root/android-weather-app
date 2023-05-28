package com.example.retrofitsandbox.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class CityStorage {
    public static final String STORAGE_NAME = "cityStorage";
    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init(Context context1) {
        context = context1;
    }

    private static void init() {
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public static int size() {
        if (settings == null) {
            init();
        }
        return settings.getAll().size();
    }

    public static void addProperty(String name, String value) {
        if (settings == null) {
            init();
        }
        editor.putString(name, value);
        editor.apply();
    }

    public static void addDefaultProperties() {
        if (settings == null) {
            init();
        }
        editor.putString("Moscow", "Moscow");
        editor.putString("Saint Petersburg", "Saint Petersburg");
        editor.apply();
    }

    public static String getProperty(String name) {
        if (settings == null) {
            init();
        }
        return settings.getString(name, null);
    }

    public static ArrayList<String> getAllProperty() {
        if (settings == null) {
            init();
        }
        ArrayList<String> cities = new ArrayList<>();
        for (Object value : settings.getAll().values()) {
            cities.add((String) value);
        }
        return cities;
    }
    public static void pop(String key) {
        editor.remove(key);
        editor.apply();
    }
}
