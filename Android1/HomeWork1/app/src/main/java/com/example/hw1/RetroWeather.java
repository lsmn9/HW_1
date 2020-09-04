package com.example.hw1;

import com.example.hw1.Weather.WeatherReq;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroWeather {
    @GET("data/2.5/weather")
    Call <WeatherReq> loadWeather(@Query("q") String cityCountry, @Query("appid") String keyApi);
}
