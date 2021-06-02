package com.example.mfinal.mvp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deaths {

    public Deaths() {
    }

    @SerializedName("new")
    @Expose
    private Integer newDeath;

    public Integer getNewDeath() {
        return newDeath;
    }
    public void setNewDeath(Integer newDeath) {
        this.newDeath = newDeath;
    }

    @SerializedName("total")
    @Expose
    private Integer totalDeath;

    public Integer getTotalDeath() {
        return totalDeath;
    }
    public void setTotalDeath(Integer totalDeath) {
        this.totalDeath = totalDeath;
    }

    @SerializedName("1M_pop")
    @Expose
    private Integer oneMPopDeath;

    public Integer getOneMPopDeath() {return oneMPopDeath; }
    public void setOneMPopDeath(Integer oneMPopDeath) {this.oneMPopDeath = oneMPopDeath;}

}
