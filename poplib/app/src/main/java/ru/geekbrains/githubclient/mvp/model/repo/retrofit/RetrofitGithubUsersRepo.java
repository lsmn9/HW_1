package ru.geekbrains.githubclient.mvp.model.repo.retrofit;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.geekbrains.githubclient.mvp.model.api.IDataSource;
import ru.geekbrains.githubclient.mvp.model.cache.IUsersCache;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.entity.room.Database;
import ru.geekbrains.githubclient.mvp.model.entity.room.RoomGithubUser;
import ru.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;


public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    private IDataSource api;
    private INetworkStatus networkStatus;
    private Database db;
    private IUsersCache usersCache;

    public RetrofitGithubUsersRepo(IDataSource api, INetworkStatus status, Database database) {
        this.api = api;
        this.networkStatus = status;
        this.db = database;
        this.usersCache = new RoomGithubUsersCache(api, database);
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return networkStatus.isOnlineSingle().flatMap((isOnline) -> usersCache.getUsers(isOnline)).subscribeOn(Schedulers.io());
    }
}





