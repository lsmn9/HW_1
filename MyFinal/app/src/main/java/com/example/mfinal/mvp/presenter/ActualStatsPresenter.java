package com.example.mfinal.mvp.presenter;

import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.view.ActStatsView;

import javax.inject.Inject;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class ActualStatsPresenter extends MvpPresenter<ActStatsView> {

    private  String actualCases = SearchPresenter.getActCases();

    @Inject
    Router router;

    public ActualStatsPresenter(){
        CovidStatsApp.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public String getActualCases() {
        return actualCases;
    }
}
