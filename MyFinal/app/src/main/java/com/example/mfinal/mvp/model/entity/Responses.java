package com.example.mfinal.mvp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Responses {

    @SerializedName("response")
    @Expose private List<Massive> responses;

    public List<Massive> getResponses() {
        return responses;
    }

    public Responses(){
    }

// там в api такой переход [response]->{0}->{country, cases[], etc}
    // не придумал как его обойти, сделал так
    public class Massive{

        public Massive(){}

        @SerializedName("cases")
        @Expose private Cases cases;

        @SerializedName("country")
        @Expose private String country;

        public String getDay() {
            return day;
        }

        @SerializedName("day")
        @Expose private String day;


        public Cases getCases() {
            return cases;
        }

        public String getCountry(){return  country;}
    }

}
