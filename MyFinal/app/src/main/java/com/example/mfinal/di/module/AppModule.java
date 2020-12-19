package com.example.mfinal.di.module;

import com.example.mfinal.CovidStatsApp;


import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;


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

    @Provides
    public Scheduler mainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

}