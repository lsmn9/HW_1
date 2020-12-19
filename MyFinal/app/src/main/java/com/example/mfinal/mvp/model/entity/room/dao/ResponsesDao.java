package com.example.mfinal.mvp.model.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mfinal.mvp.model.entity.room.RoomResponses;

import java.util.List;


@Dao
public interface ResponsesDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(RoomResponses roomResponses);
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(List<RoomResponses> roomResponses);

        @Update
        void update(RoomResponses roomResponses);
        @Update
        void update(List<RoomResponses> roomResponses);

        @Delete
        void delete(RoomResponses roomResponses);
        @Delete
        void delete(List<RoomResponses> roomResponses);

        @Query("SELECT * FROM RoomResponses")
        RoomResponses getAll();

        @Query("SELECT * FROM RoomResponses WHERE country = :country LIMIT 1")
        RoomResponses findByCountry(String country);

}
