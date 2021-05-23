package ru.geekbrains.githubclient.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.githubclient.mvp.model.cache.IRepoCache;
import ru.geekbrains.githubclient.mvp.model.cache.IUsersCache;
import ru.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.model.repo.IRepoRepo;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitRepoRepo;

@Module
public class RepoModule {
    @Singleton
    @Provides
    public IGithubUsersRepo usersRepo(INetworkStatus status, IUsersCache cache) {
        return new RetrofitGithubUsersRepo( status, cache);
    }

    @Singleton
    @Provides
    public IRepoRepo repoRepo(INetworkStatus networkStatus, IRepoCache cache) {
        return new RetrofitRepoRepo( networkStatus, cache);
    }
}
