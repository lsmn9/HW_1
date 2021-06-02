package com.example.mfinal.mvp.presenter;

import com.example.mfinal.mvp.model.entity.Responses;
import com.example.mfinal.mvp.model.entity.room.RoomResponses;
import com.example.mfinal.mvp.view.CasesView;


import moxy.MvpPresenter;


public class CasesPresenter extends MvpPresenter<CasesView> {

    private String totalCases, newCases, activeCases, recoveredCases, oneMPopCases, criticalCases;

    public String getTotalCases() {return totalCases;}
    public String getNewCases() {return newCases;}
    public String getOneMPopCases() {return oneMPopCases;}
    public String getActiveCases() {return activeCases;}
    public String getRecoveredCases() {return recoveredCases;}
    public String getCriticalCases() {return criticalCases;}


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void checkConnection() {

        boolean connection = StatsPresenter.isConnection();

        if (connection) {
            Responses response = StatsPresenter.getResponse();
            oneMPopCases = String.valueOf(response.getResponses().get(0).getCases().getOneMPopCases());
            totalCases = String.valueOf(response.getResponses().get(0).getCases().getTotalCases());
            newCases = String.valueOf(response.getResponses().get(0).getCases().getNewCases());
            recoveredCases = String.valueOf(response.getResponses().get(0).getCases().getRecoveredCases());
            criticalCases = String.valueOf(response.getResponses().get(0).getCases().getCriticalCases());
            activeCases = String.valueOf(response.getResponses().get(0).getCases().getActiveCases());

        } else {
            RoomResponses roomResponses = StatsPresenter.getRoomResponses();
            oneMPopCases = String.valueOf(roomResponses.getCases().getOneMPopCases());
            totalCases = String.valueOf(roomResponses.getCases().getTotalCases());
            newCases = String.valueOf(roomResponses.getCases().getNewCases());
            recoveredCases = String.valueOf(roomResponses.getCases().getRecoveredCases());
            criticalCases = String.valueOf(roomResponses.getCases().getCriticalCases());
            activeCases = String.valueOf(roomResponses.getCases().getActiveCases());
        }
    }

}
