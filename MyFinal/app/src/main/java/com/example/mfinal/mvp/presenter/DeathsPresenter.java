package com.example.mfinal.mvp.presenter;

import com.example.mfinal.mvp.model.entity.Responses;
import com.example.mfinal.mvp.model.entity.room.RoomResponses;
import com.example.mfinal.mvp.view.DeathsView;


import moxy.MvpPresenter;


public class DeathsPresenter extends MvpPresenter<DeathsView> {

    private String totalDeaths, newDeaths, oneMPopDeaths;

    public String getTotalDeaths() {return totalDeaths;}
    public String getNewDeaths() {return newDeaths;}
    public String getOneMPopDeaths() {return oneMPopDeaths;}


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void checkConnection() {

        boolean connection = StatsPresenter.isConnection();

        if (connection) {
            Responses response = StatsPresenter.getResponse();
            oneMPopDeaths = String.valueOf(response.getResponses().get(0).getDeaths().getOneMPopDeath());
            totalDeaths = String.valueOf(response.getResponses().get(0).getDeaths().getTotalDeath());
            newDeaths = String.valueOf(response.getResponses().get(0).getDeaths().getNewDeath());

        } else {
            RoomResponses roomResponses = StatsPresenter.getRoomResponses();
            newDeaths = String.valueOf(roomResponses.getDeaths().getNewDeath());
            oneMPopDeaths = String.valueOf(roomResponses.getDeaths().getOneMPopDeath());
            totalDeaths = String.valueOf(roomResponses.getDeaths().getTotalDeath());
        }
    }
}