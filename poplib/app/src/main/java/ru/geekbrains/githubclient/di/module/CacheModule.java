package ru.geekbrains.githubclient.di.module;

import androidx.room.Room;
import androidx.room.migration.Migration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.api.IDataSource;
import ru.geekbrains.githubclient.mvp.model.cache.IRepoCache;
import ru.geekbrains.githubclient.mvp.model.cache.IUsersCache;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepoCache;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import ru.geekbrains.githubclient.mvp.model.entity.room.Database;

@Module
public class CacheModule {

    @Singleton
    @Provides
    Database database() {
        return Room.databaseBuilder(GithubApplication.INSTANCE, Database.class, Database.DB_NAME)
                .build();
    }

    @Singleton
    @Provides
    IUsersCache usersCache(IDataSource api,Database db) {
        return new RoomGithubUsersCache(api,db);
    }

    @Singleton
    @Provides
    IRepoCache userRepositoriesCache(IDataSource api, Database db) {
        return new RoomGithubRepoCache(api,db);
    }



}
