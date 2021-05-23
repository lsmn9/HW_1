package com.example.mfinal.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mfinal.mvp.model.entity.Cases;
import com.example.mfinal.mvp.model.entity.Deaths;

@Entity
public class RoomResponses {

    @PrimaryKey
    @NonNull
    private String country;

    private String day;

    @Embedded
    private Cases cases;

    @Embedded
    private  Deaths deaths;

    public RoomResponses(){}

    public RoomResponses(String country, Cases cases, Deaths deaths, String day){
        this.cases = cases;
        this.country = country;
        this.deaths = deaths;
        this.day = day;
    }

    public Cases getCases() {
        return cases;
    }
    public void setCases(Cases cases) {
        this.cases = cases;
    }

    public String getCountry(){ return country; }
    public void setCountry(String country) {
        this.country = country;
    }

    public Deaths getDeaths() {
        return deaths;
    }
    public void setDeaths(Deaths deaths) {this.deaths = deaths;}

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }



}
