package com.example.mfinal.mvp.model.entity.room;

import androidx.room.RoomDatabase;


import com.example.mfinal.mvp.model.entity.room.dao.ResponsesDao;

@androidx.room.Database(entities = {RoomResponses.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public static final String DB_NAME = "database.db";
    private static volatile Database INSTANCE;
    public abstract ResponsesDao responsesDao();

}
