package ru.geekbrains.githubclient.mvp.model.cache;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo;

public interface IRepoCache {
    Single<List<GithubUserRepo>> getRepos(boolean isOnline, String login);
}
