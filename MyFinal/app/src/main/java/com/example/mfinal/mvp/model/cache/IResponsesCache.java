package com.example.mfinal.mvp.model.cache;

import com.example.mfinal.mvp.model.entity.Cases;


public interface IResponsesCache {
    void putResponses(String country, Cases cases, String Day);
    Integer getActiveCases(String country);
    String getLastDay(String country);
}
