package ru.geekbrains.githubclient.mvp.model.repo.retrofit;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.cache.IUsersCache;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;


public class RetrofitGithubUsersRepo implements IGithubUsersRepo {

    @Inject
    INetworkStatus networkStatus;

    @Inject
    IUsersCache usersCache;

    public RetrofitGithubUsersRepo(INetworkStatus status, IUsersCache usersCache) {
        GithubApplication.INSTANCE.getAppComponent().inject(this);

    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return networkStatus.isOnlineSingle().flatMap((isOnline) -> usersCache
                .getUsers(isOnline)).subscribeOn(Schedulers.io());
    }
}





