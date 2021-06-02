package com.example.matdis.POD

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface PictureOfTheDayAPI {
    @GET("planetary/earth/assets")
    fun getPictureOfMyTown(@Query("lon") lon:Float,
                           @Query("lat") lat:Float,
                           @Query("date") date:String,
                           @Query("dim") dim: Float,
                           @Query("api_key") apiKey: String): Call<PODServerResponseData>
    @GET("planetary/apod")
    fun getPictureofDay(@Query("api_key") apiKey: String): Call<PODServerResponseData>
}