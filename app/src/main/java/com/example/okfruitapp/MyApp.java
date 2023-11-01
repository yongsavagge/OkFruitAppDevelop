package com.example.okfruitapp;

import android.app.Application;

public class MyApp extends Application {
    private static String BASE_URL = "http://192.168.1.7:5000/"; // Reemplaza con tu URL base

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }
}
