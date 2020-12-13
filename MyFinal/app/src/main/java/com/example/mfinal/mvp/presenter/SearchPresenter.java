package com.example.mfinal.mvp.presenter;

import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.model.api.IDataSource;
import com.example.mfinal.mvp.model.cache.IResponsesCache;
import com.example.mfinal.mvp.model.entity.Responses;
import com.example.mfinal.mvp.view.SearchView;
import com.example.mfinal.navigation.Screens;

import javax.inject.Inject;

import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.terrakok.cicerone.Router;

public class SearchPresenter  extends MvpPresenter<SearchView> {

    @Inject
    IResponsesCache responsesCache;

    @Inject
    IDataSource api;

    @Inject
    Router router;

    private static String actCases;

    public SearchPresenter(){
        CovidStatsApp.INSTANCE.getAppComponent().inject(this);
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }


    public void afterPush(String country){

        api.getResponses(country)
                .enqueue(new Callback<Responses>() {
                    @Override
                    public void onResponse(Call<Responses> call, Response<Responses> response) {
                        if (response.body() != null){
                            // при неправильном написании прилетает нулевой массив
                            if (!response.body().getResponses().isEmpty()){
                                // по нажатию на кнопку получим действующие заражения
                                actCases = String.valueOf(response.body().getResponses().get(0)
                                        .getCases().getActiveCases());
                                System.out.println(actCases + " after ok");// вместо логов
                                // ложим данные в базу
                                responsesCache.putResponses(country,
                                        response.body().getResponses().get(0).getCases(),
                                        response.body().getResponses().get(0).getDay());
                                // переходим на экран
                                router.navigateTo(new Screens.ActStatsScreen());
                            }
                            else { // неправильное написание страны
                                actCases = "Bad way, check country";
                                System.out.println("Bad way, check country");}
                        }
                        else { // если нулл прилетает
                            System.out.println("No such country in the base, try again");
                            actCases = "No such country in the base, try again";}
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                        Integer active = responsesCache.getActiveCases(country);
                        String day = responsesCache.getLastDay(country);
                        // если страна есть в базе
                        if (active != null){
                            // если нет подключения передаем еще и крайний день из базы
                            actCases = String.valueOf(active) + "\n(" + day+")";
                            router.navigateTo(new Screens.ActStatsScreen());
                        }
                        // если  нет в базе, просто просим проверить подключение
                        else  actCases = "Check connection";
                        System.out.println("Trowable: " + t);
                    }
                });
    }

    public static String getActCases() {
        return actCases;
    }

}
