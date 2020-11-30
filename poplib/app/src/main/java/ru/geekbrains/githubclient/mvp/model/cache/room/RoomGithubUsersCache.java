package ru.geekbrains.githubclient.mvp.model.cache.room;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.mvp.model.api.IDataSource;
import ru.geekbrains.githubclient.mvp.model.cache.IUsersCache;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.entity.room.Database;
import ru.geekbrains.githubclient.mvp.model.entity.room.RoomGithubUser;

public class RoomGithubUsersCache implements IUsersCache {

    private IDataSource api;
    private Database db;

    public  RoomGithubUsersCache(IDataSource api, Database db){
        this.api = api;
        this.db = db;
    }


    @Override
    public Single<List<GithubUser>> getUsers(boolean isOnline) {
            if (isOnline) {
                return api.getUsers().flatMap((users) -> {
                    return Single.fromCallable(() -> {
                        List<RoomGithubUser> roomGithubUsers = new ArrayList<>();

                        for (GithubUser user: users) {
                            RoomGithubUser roomUser = new RoomGithubUser(user.getId(),
                                    user.getLogin(),
                                    user.getAvatarUrl(),
                                    user.getReposUrl());

                            roomGithubUsers.add(roomUser);
                        }

                        db.userDao().insert(roomGithubUsers);

                        return users;
                    });
                });
            } else {
                return Single.fromCallable(() -> {
                    List<GithubUser> users = new ArrayList<>();

                    List<RoomGithubUser> roomGithubUsers = db.userDao().getAll();

                    for (RoomGithubUser roomGithubUser : roomGithubUsers) {
                        GithubUser githubUser = new GithubUser(roomGithubUser.getLogin(),
                                roomGithubUser.getAvatarUrl(),
                                roomGithubUser.getReposUrl());
                        users.add(githubUser);
                    }

                    return users;
                });
            }
    }
}
