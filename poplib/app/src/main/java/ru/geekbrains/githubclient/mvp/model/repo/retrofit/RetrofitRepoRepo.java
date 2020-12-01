package ru.geekbrains.githubclient.mvp.model.repo.retrofit;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.cache.IRepoCache;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo;
import ru.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import ru.geekbrains.githubclient.mvp.model.repo.IRepoRepo;

public class RetrofitRepoRepo implements IRepoRepo {

    @Inject
    INetworkStatus networkStatus;

    @Inject
    IRepoCache repoCache;

    public RetrofitRepoRepo(INetworkStatus status, IRepoCache repoCache) {
        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }


    @Override
    public Single<List<GithubUserRepo>> getUsersRepo(String login) {
        return networkStatus.isOnlineSingle().flatMap((isOnline)->repoCache
                .getRepos(isOnline, login))
                .subscribeOn(Schedulers.io());
    }
}
