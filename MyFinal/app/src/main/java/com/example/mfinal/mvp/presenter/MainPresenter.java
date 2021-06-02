package com.example.mfinal.mvp.presenter;


import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.view.MainView;
import com.example.mfinal.navigation.Screens;

import javax.inject.Inject;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView>  {

    @Inject
    Router router;

    public MainPresenter() {
        super();
        CovidStatsApp.INSTANCE.getAppComponent().inject(this);
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router.replaceScreen(new Screens.SearchScreen());

    }

    public void backClicked() {
        router.exit();
    }



}
