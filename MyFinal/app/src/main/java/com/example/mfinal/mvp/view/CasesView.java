package com.example.mfinal.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

public interface CasesView extends MvpView {
    @AddToEndSingle
    void init();
}
