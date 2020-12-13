package com.example.mfinal.mvp.model.cache.room;

import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.model.api.IDataSource;
import com.example.mfinal.mvp.model.cache.IResponsesCache;
import com.example.mfinal.mvp.model.entity.Cases;
import com.example.mfinal.mvp.model.entity.room.Database;
import com.example.mfinal.mvp.model.entity.room.RoomResponses;

import javax.inject.Inject;


public class RoomResposesCache implements IResponsesCache {

    @Inject
    IDataSource api;

    @Inject
    Database db;

    public RoomResposesCache(IDataSource api, Database db){
        CovidStatsApp.INSTANCE.getAppComponent().inject(this);
    }


    @Override
    public void putResponses(String country, Cases cases, String day) {
        RoomResponses roomResponses= new RoomResponses(country, cases, day);
        db.resposesDao().insert(roomResponses);
    }

    @Override
    public Integer getActiveCases(String country) {
        return db.resposesDao().findCasesByCountry(country);
    }

    @Override
    public String getLastDay(String country) {
        return db.resposesDao().findLastDayByCountry(country);
    }


}
