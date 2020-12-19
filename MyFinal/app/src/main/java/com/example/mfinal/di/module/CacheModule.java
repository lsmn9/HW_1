package com.example.mfinal.di.module;


import androidx.room.Room;

import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.model.api.IDataSource;
import com.example.mfinal.mvp.model.cache.IResponsesCache;
import com.example.mfinal.mvp.model.cache.room.RoomResposesCache;
import com.example.mfinal.mvp.model.entity.room.Database;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


    @Module
    public class CacheModule {

        @Singleton
        @Provides
        Database database() {
            return Room.databaseBuilder(CovidStatsApp.INSTANCE, Database.class, Database.DB_NAME)
                    .build();
        }

        @Singleton
        @Provides
        IResponsesCache responsesCache(IDataSource api, Database db) {
            return new RoomResposesCache(api,db);
        }

    }
