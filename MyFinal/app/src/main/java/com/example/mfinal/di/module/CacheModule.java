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

        //TODO: убрать ui поток!!!! сделаю, используя Rx, пока просто проверить нужно было
        @Singleton
        @Provides
        Database database() {
            return Room.databaseBuilder(CovidStatsApp.INSTANCE, Database.class, Database.DB_NAME)
                    // пока четко не определился, что лежать будет в базе,
                    //периодически использую, чтобы кучу миграций не делать. Уберу.
                   // .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        @Singleton
        @Provides
        IResponsesCache responsesCache(IDataSource api, Database db) {
            return new RoomResposesCache(api,db);
        }

    }
