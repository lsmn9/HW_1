package com.example.mfinal.di.module;

import com.example.mfinal.CovidStatsApp;


import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    private CovidStatsApp app;

    public AppModule(CovidStatsApp app) {
        this.app = app;
    }

    @Provides
    public CovidStatsApp app() {
        return app;
    }

}