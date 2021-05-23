package com.example.mfinal.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

public interface SearchView extends MvpView {
    @AddToEndSingle
    void init();

    @AddToEndSingle
    void onShowBtnClick();

}
