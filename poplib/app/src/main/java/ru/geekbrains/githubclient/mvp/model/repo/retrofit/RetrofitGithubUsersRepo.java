package ru.geekbrains.githubclient.mvp.model.repo.retrofit;


import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.geekbrains.githubclient.mvp.model.api.IDataSource;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;


public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    private IDataSource api;


    public RetrofitGithubUsersRepo(IDataSource api) {
        this.api = api;
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return api.getUsers().subscribeOn(Schedulers.io());
    }





}