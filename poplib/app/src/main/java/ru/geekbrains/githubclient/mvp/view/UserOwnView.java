package ru.geekbrains.githubclient.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface UserOwnView extends MvpView {
    void init();
    void updateList();
}
