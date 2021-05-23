package ru.geekbrains.githubclient.mvp.model.entity.room;

import androidx.room.RoomDatabase;

import ru.geekbrains.githubclient.mvp.model.entity.room.dao.RepositoryDao;
import ru.geekbrains.githubclient.mvp.model.entity.room.dao.UserDao;

@androidx.room.Database(entities = {RoomGithubUser.class, RoomGithubRepository.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public static final String DB_NAME = "database.db";
    private static volatile Database INSTANCE;
    public abstract UserDao userDao();
    public abstract RepositoryDao repositoryDao();

}