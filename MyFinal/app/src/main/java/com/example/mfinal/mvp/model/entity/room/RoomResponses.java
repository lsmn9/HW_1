package com.example.mfinal.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mfinal.mvp.model.entity.Cases;

@Entity
public class RoomResponses {




    @PrimaryKey
    @NonNull
    private String country;


    private String day;

    @Embedded
    private Cases cases;

    public RoomResponses(){}

    public RoomResponses(String country, Cases cases, String day){
        this.cases = cases;
        this.country = country;
        this.day = day;
    }

    public Cases getCases() {
        return cases;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry(){
        return country;
    }

    public void setCases(Cases cases) {
        this.cases = cases;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }



}
