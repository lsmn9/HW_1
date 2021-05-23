package com.example.mfinal.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

public interface StatsView extends MvpView {

    @AddToEndSingle
    void init();
    @AddToEndSingle
    void onCasesClick();
    @AddToEndSingle
    void onDeathClick();
}
