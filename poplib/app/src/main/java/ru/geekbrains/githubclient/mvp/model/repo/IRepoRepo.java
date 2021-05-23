package ru.geekbrains.githubclient.mvp.model.repo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo;

public interface IRepoRepo {

    Single<List<GithubUserRepo>> getUsersRepo(String login);
}
