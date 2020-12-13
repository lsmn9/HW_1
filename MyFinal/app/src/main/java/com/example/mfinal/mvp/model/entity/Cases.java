package com.example.mfinal.mvp.model.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cases {
    @SerializedName("active")
    @Expose
    private Integer activeCase;

    public Cases(Integer activeCase) {
        this.activeCase = activeCase;
    }

    public Integer getActiveCases(){return activeCase;}
}
