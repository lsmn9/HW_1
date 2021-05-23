package ru.geekbrains.githubclient;

import android.app.Application;

import ru.geekbrains.githubclient.di.AppComponent;
import ru.geekbrains.githubclient.di.DaggerAppComponent;
import ru.geekbrains.githubclient.di.module.AppModule;
import ru.geekbrains.githubclient.mvp.model.api.IDataSource;

public class GithubApplication extends Application {
    public static final boolean DEBUG = true;
    public static GithubApplication INSTANCE;
    private ApiHolder apiHolder;
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        apiHolder = new ApiHolder();

    }

    public AppComponent getAppComponent() { return appComponent;}

    public IDataSource getApi() { return apiHolder.getDataSource();}


}
