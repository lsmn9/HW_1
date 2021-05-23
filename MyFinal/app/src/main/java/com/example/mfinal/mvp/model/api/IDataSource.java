package com.example.mfinal.mvp.model.api;

import com.example.mfinal.mvp.model.entity.Responses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IDataSource {
    @Headers({"x-rapidapi-key: 5f22da48cdmsh5a73c4ec7baaa99p13575ajsn0d157f8c4653",
              "x-rapidapi-host: covid-193.p.rapidapi.com"})
    @GET("/statistics")
    Call<Responses> getResponses(@Query("country") String country);
}
