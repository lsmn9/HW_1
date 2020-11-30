package ru.geekbrains.githubclient.mvp.presenter;

import moxy.MvpPresenter;
import ru.geekbrains.githubclient.mvp.view.RepoView;

public class RepoPresenter extends MvpPresenter<RepoView> {

    private String repoName = UserOwnPresenter.getRepoName();

    private int forksCount = UserOwnPresenter.getForksCount();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();

    }

    public boolean backPressed() {
        return true;

    }

    public String getRepoName() {
        return repoName;
    }

    public int getForksCount() {
        return forksCount;
    }

}
