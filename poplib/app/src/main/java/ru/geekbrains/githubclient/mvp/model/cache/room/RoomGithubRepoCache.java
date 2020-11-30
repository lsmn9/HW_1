package ru.geekbrains.githubclient.mvp.model.cache.room;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.mvp.model.api.IDataSource;
import ru.geekbrains.githubclient.mvp.model.cache.IRepoCache;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo;
import ru.geekbrains.githubclient.mvp.model.entity.room.Database;
import ru.geekbrains.githubclient.mvp.model.entity.room.RoomGithubRepository;


public class RoomGithubRepoCache implements IRepoCache {

    private IDataSource api;
    private Database db;

    public  RoomGithubRepoCache(IDataSource api, Database db){
        this.api = api;
        this.db = db;
    }


    @Override
    public Single<List<GithubUserRepo>> getRepos(boolean isOnline, String login) {
        if (isOnline) {
            return api.getUsersRepo(login).flatMap((userRepos) -> {
                return Single.fromCallable(() -> {
                    List<RoomGithubRepository> roomGithubRepositories = new ArrayList<>();

                    for (GithubUserRepo userRepo: userRepos) {
                        RoomGithubRepository roomUserRepo = new RoomGithubRepository(userRepo.getId(),
                                userRepo.getName(),
                                userRepo.getForksCount());

                        roomGithubRepositories.add(roomUserRepo);
                    }

                    db.repositoryDao().insert(roomGithubRepositories);

                    return userRepos;
                });
            });
        } else {
            return Single.fromCallable(() -> {
                List<GithubUserRepo> userRepos = new ArrayList<>();

                List<RoomGithubRepository> roomGithubRepositories = db.repositoryDao().getAll();

                for (RoomGithubRepository roomGithubRepository : roomGithubRepositories) {
                    GithubUserRepo githubUserRepo = new GithubUserRepo(roomGithubRepository.name,
                            roomGithubRepository.forksCount);
                    userRepos.add(githubUserRepo);
                }

                return userRepos;
            });
        }
    }
}
