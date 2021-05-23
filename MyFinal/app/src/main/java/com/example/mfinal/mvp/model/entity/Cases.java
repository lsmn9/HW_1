package com.example.mfinal.mvp.model.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cases {

    public Cases() {
    }

    @SerializedName("new")
    @Expose
    private Integer newCases;

    public Integer getNewCases() {
        return newCases;
    }
    public void setNewCases(Integer newCases) {
        this.newCases = newCases;
    }

    @SerializedName("active")
    @Expose
    private Integer activeCases;

    public Integer getActiveCases() {
        return activeCases;
    }
    public void setActiveCases(Integer activeCases) {
        this.activeCases = activeCases;
    }

    @SerializedName("critical")
    @Expose
    private Integer criticalCases;

    public Integer getCriticalCases() {
        return criticalCases;
    }
    public void setCriticalCases(Integer criticalCases) {
        this.criticalCases = criticalCases;
    }

    @SerializedName("recovered")
    @Expose
    private Integer recoveredCases;

    public Integer getRecoveredCases() {
        return recoveredCases;
    }
    public void setRecoveredCases(Integer recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    @SerializedName("total")
    @Expose
    private Integer totalCases;

    public Integer getTotalCases() {
        return totalCases;
    }
    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    @SerializedName("1M_pop")
    @Expose
    private Integer oneMPopCases;

    public Integer getOneMPopCases() {
        return oneMPopCases;
    }
    public void setOneMPopCases(Integer oneMPopCases) {
        this.oneMPopCases = oneMPopCases;
    }

}
