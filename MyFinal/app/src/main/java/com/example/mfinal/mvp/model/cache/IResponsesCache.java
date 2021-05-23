package com.example.mfinal.mvp.model.cache;

import com.example.mfinal.mvp.model.entity.Responses;
import com.example.mfinal.mvp.model.entity.room.RoomResponses;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;


public interface IResponsesCache {
    Completable putResponses(Responses responses);
    Single<RoomResponses> getAllItems(String country);
}
