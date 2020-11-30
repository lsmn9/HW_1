package ru.geekbrains.githubclient;

import android.app.Application;

import ru.geekbrains.githubclient.mvp.model.api.IDataSource;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class GithubApplication extends Application {
    public static final boolean DEBUG = true;
    public static GithubApplication INSTANCE;
    private ApiHolder apiHolder;

    private Cicerone<Router> cicerone;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        initCicerone();
        apiHolder = new ApiHolder();
    }

    public static GithubApplication getApplication() {
        return INSTANCE;
    }

    private void initCicerone() {
        cicerone = Cicerone.create();
    }

    public Router getRouter() {
        return cicerone.getRouter();
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public IDataSource getApi() { return apiHolder.getDataSource();}


}
