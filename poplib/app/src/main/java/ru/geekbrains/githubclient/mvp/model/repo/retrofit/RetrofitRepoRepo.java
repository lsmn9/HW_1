package ru.geekbrains.githubclient.mvp.model.repo.retrofit;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.mvp.model.api.IRepoSource;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo;
import ru.geekbrains.githubclient.mvp.model.repo.IRepoRepo;

public class RetrofitRepoRepo implements IRepoRepo {

    private IRepoSource repoApi;
    private String login;

    public RetrofitRepoRepo(IRepoSource repoApi, String login) {
        this.repoApi = repoApi;
        this.login = login;
    }

    @Override
    public Single<List<GithubUserRepo>> getUsersRepo() {
        return repoApi.getUsersRepo(login);
    }
}
