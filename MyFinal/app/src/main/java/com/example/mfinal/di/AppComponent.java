package com.example.mfinal.di;

import com.example.mfinal.ApiHolder;
import com.example.mfinal.MainActivity;
import com.example.mfinal.di.module.ApiModule;
import com.example.mfinal.di.module.AppModule;
import com.example.mfinal.di.module.CacheModule;
import com.example.mfinal.di.module.CiceroneModule;
import com.example.mfinal.mvp.model.cache.room.RoomResposesCache;
import com.example.mfinal.mvp.model.repo.retrofit.RetrofitResponses;
import com.example.mfinal.mvp.presenter.ActualStatsPresenter;
import com.example.mfinal.mvp.presenter.MainPresenter;
import com.example.mfinal.mvp.presenter.SearchPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApiModule.class,
                AppModule.class,
                CiceroneModule.class,
                CacheModule.class
        }
)

public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);
    void inject(ApiHolder apiHolder);
    void inject(ActualStatsPresenter actualStatsPresenter);
    void inject (SearchPresenter searchPresenter);
    void inject (RoomResposesCache roomResposesCache);
    void inject (RetrofitResponses retrofitResponses);

}