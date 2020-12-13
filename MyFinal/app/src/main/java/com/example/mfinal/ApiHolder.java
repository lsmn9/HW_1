package com.example.mfinal;

import com.example.mfinal.mvp.model.api.IDataSource;

import javax.inject.Inject;


public class ApiHolder {

    private IDataSource response;

    @Inject
    IDataSource api;

    ApiHolder() {

        CovidStatsApp.INSTANCE.getAppComponent().inject(this);
    }


    public IDataSource getResponse() {
        return response;
    }
}
