package com.example.mfinal.mvp.model.cache.room;

import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.model.api.IDataSource;
import com.example.mfinal.mvp.model.cache.IResponsesCache;
import com.example.mfinal.mvp.model.entity.Responses;
import com.example.mfinal.mvp.model.entity.room.Database;
import com.example.mfinal.mvp.model.entity.room.RoomResponses;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RoomResposesCache implements IResponsesCache {

    @Inject
    IDataSource api;

    @Inject
    Database db;

    public RoomResposesCache(IDataSource api, Database db) {
        CovidStatsApp.INSTANCE.getAppComponent().inject(this);
    }


    @Override
    public Completable putResponses(Responses responses) {
        return Completable.fromAction(() -> {
            RoomResponses roomResponses = new RoomResponses(
                    responses.getResponses().get(0).getCountry().toUpperCase(),
                    responses.getResponses().get(0).getCases(),
                    responses.getResponses().get(0).getDeaths(),
                    responses.getResponses().get(0).getDay());
            System.out.println(roomResponses.getCases().getTotalCases() + roomResponses.getCountry());
            db.responsesDao().insert(roomResponses);
            System.out.println(db.responsesDao().findByCountry(roomResponses.getCountry()));
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<RoomResponses> getAllItems(String country) {
        return Single.fromCallable(() ->
        {
            return db.responsesDao().findByCountry(country);
        }).subscribeOn(Schedulers.io());
    }
}
