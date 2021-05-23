package ru.geekbrains.githubclient.mvp.model.cache;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;

public interface IUsersCache {
    Single<List<GithubUser>> getUsers(boolean isOnline);
}
