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
public interface ResposesDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(RoomResponses user);
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(List<RoomResponses> users);

        @Update
        void update(RoomResponses user);
        @Update
        void update(List<RoomResponses> users);

        @Delete
        void delete(RoomResponses user);
        @Delete
        void delete(List<RoomResponses> users);

        @Query("SELECT * FROM RoomResponses")
        List<RoomResponses> getAll();

        @Query("SELECT * FROM RoomResponses WHERE country = :country LIMIT 5")
        RoomResponses findByCountry(String country);

        @Query("SELECT activeCase FROM RoomResponses WHERE country = :country LIMIT 5")
        Integer findCasesByCountry(String country);

        @Query("SELECT day FROM RoomResponses WHERE country = :country LIMIT 5")
        String findLastDayByCountry(String country);

}
