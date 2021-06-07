package ru.geekbrains.githubclient.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.geekbrains.githubclient.ApiHolder;
import ru.geekbrains.githubclient.MainActivity;
import ru.geekbrains.githubclient.di.module.ApiModule;
import ru.geekbrains.githubclient.di.module.AppModule;
import ru.geekbrains.githubclient.di.module.CacheModule;
import ru.geekbrains.githubclient.di.module.CiceroneModule;
import ru.geekbrains.githubclient.di.module.RepoModule;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepoCache;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitRepoRepo;
import ru.geekbrains.githubclient.mvp.presenter.MainPresenter;
import ru.geekbrains.githubclient.mvp.presenter.RepoPresenter;
import ru.geekbrains.githubclient.mvp.presenter.UserOwnPresenter;
import ru.geekbrains.githubclient.mvp.presenter.UsersPresenter;
import ru.geekbrains.githubclient.ui.fragments.UsersFragment;


@Singleton
@Component (
        modules = {
                ApiModule.class,
                AppModule.class,
                CacheModule.class,
                CiceroneModule.class,
                RepoModule.class
        }
)

public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);
    void inject(UsersPresenter usersPresenter);
    void inject(UserOwnPresenter userOwnPresenter);
    void inject(ApiHolder apiHolder);
    void inject(RepoPresenter repoPresenter);
    void inject(RetrofitGithubUsersRepo retrofitGithubUsersRepo);
    void inject(RetrofitRepoRepo retrofitRepoRepo);
    void inject(RoomGithubUsersCache roomGithubUsersCache);
    void inject(RoomGithubRepoCache roomRepoCache);
    void inject(UsersFragment usersFragment);
}
