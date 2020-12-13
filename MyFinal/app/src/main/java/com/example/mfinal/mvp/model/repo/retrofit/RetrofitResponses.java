package com.example.mfinal.mvp.model.repo.retrofit;

import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.model.cache.IResponsesCache;
import com.example.mfinal.mvp.model.network.INetworkStatus;

import javax.inject.Inject;

public class RetrofitResponses {

    @Inject
    IResponsesCache responsesCache;

    @Inject
    INetworkStatus networkStatus;

    public RetrofitResponses(IResponsesCache responsesCache) {
       CovidStatsApp.INSTANCE.getAppComponent().inject(this);

    }


}


