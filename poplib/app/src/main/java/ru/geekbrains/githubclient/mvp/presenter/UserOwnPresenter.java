package ru.geekbrains.githubclient.mvp.presenter;

import moxy.MvpPresenter;
import ru.geekbrains.githubclient.mvp.view.UserOwnView;


public class UserOwnPresenter extends MvpPresenter<UserOwnView> {


    private String login = UsersPresenter.getChosenLogin();
    public String getLogin(){
        return login;
    }
    public boolean backPressed() {
       // на будущее
        return true;

    }
}
