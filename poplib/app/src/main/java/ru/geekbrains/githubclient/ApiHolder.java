package ru.geekbrains.githubclient;

import com.google.gson.Gson;

import javax.inject.Inject;

import ru.geekbrains.githubclient.mvp.model.api.IDataSource;


public class ApiHolder {

    private IDataSource dataSource;

    @Inject
    Gson gson;

    @Inject
    IDataSource api;


    ApiHolder() {

        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }


    public IDataSource getDataSource() {
        return dataSource;
    }

}
