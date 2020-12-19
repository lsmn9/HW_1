package com.example.mfinal.di.module;

import com.example.mfinal.mvp.model.api.IDataSource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Named("baseUrl")
    @Provides
    String baseUrl() {
        return "https://covid-193.p.rapidapi.com/";
    }


    @Singleton
    @Provides
    IDataSource api(@Named("baseUrl") String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IDataSource.class);
    }

}
