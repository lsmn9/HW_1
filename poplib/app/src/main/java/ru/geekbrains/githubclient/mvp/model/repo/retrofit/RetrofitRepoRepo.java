package ru.geekbrains.githubclient.mvp.model.repo.retrofit;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.geekbrains.githubclient.mvp.model.api.IDataSource;
import ru.geekbrains.githubclient.mvp.model.cache.IRepoCache;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepoCache;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo;
import ru.geekbrains.githubclient.mvp.model.entity.room.Database;
import ru.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import ru.geekbrains.githubclient.mvp.model.repo.IRepoRepo;

public class RetrofitRepoRepo implements IRepoRepo {

    private IDataSource repoApi;
    private INetworkStatus networkStatus;
    private Database db;
    private IRepoCache repoCache;

    public RetrofitRepoRepo(IDataSource api, INetworkStatus status, Database database) {
        this.repoApi = api;
        this.networkStatus = status;
        this.db = database;
        this.repoCache = new RoomGithubRepoCache(api, database);
    }


    @Override
    public Single<List<GithubUserRepo>> getUsersRepo(String login) {
        return networkStatus.isOnlineSingle().flatMap((isOnline)->repoCache
                .getRepos(isOnline, login))
                .subscribeOn(Schedulers.io());
    }
}
