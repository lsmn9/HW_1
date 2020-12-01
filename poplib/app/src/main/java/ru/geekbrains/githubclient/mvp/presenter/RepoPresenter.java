package ru.geekbrains.githubclient.mvp.presenter;

import javax.inject.Inject;

import moxy.MvpPresenter;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.view.RepoView;
import ru.terrakok.cicerone.Router;

public class RepoPresenter extends MvpPresenter<RepoView> {

    private String repoName = UserOwnPresenter.getRepoName();
    private int forksCount = UserOwnPresenter.getForksCount();

    @Inject
    Router router;

    public RepoPresenter(){
        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();

    }

    public boolean backPressed() {
        router.exit();
        return true;

    }

    public String getRepoName() {
        return repoName;
    }

    public int getForksCount() {
        return forksCount;
    }

}
