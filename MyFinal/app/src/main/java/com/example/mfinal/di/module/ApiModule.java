package com.example.mfinal.di.module;

import com.example.mfinal.mvp.model.api.IDataSource;
import com.example.mfinal.mvp.model.network.INetworkStatus;
import com.example.mfinal.ui.network.AndroidNetworkStatus;

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

//    @Singleton
//    @Provides
//    Gson gson() {
//        return new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .excludeFieldsWithoutExposeAnnotation().create();
//    }

    @Singleton
    @Provides
    IDataSource api(@Named("baseUrl") String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IDataSource.class);
    }

    @Singleton
    @Provides
    INetworkStatus networkStatus() {
        return new AndroidNetworkStatus();
    }

}
