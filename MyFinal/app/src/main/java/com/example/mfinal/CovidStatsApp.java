package com.example.mfinal;

import android.app.Application;

import com.example.mfinal.di.AppComponent;

import com.example.mfinal.di.DaggerAppComponent;
import com.example.mfinal.di.module.AppModule;
import com.example.mfinal.mvp.model.api.IDataSource;


public class CovidStatsApp extends Application {

    public static CovidStatsApp INSTANCE;
    private ApiHolder apiHolder;
    private AppComponent appComponent;

    @Override
    public void onCreate() {

        super.onCreate();

        INSTANCE = this;

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        apiHolder = new ApiHolder();

    }

    public AppComponent getAppComponent() { return appComponent;}

    public IDataSource getApi() { return apiHolder.getResponse();}
}
