package com.example.mfinal.mvp.presenter;


import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.model.api.IDataSource;
import com.example.mfinal.mvp.model.cache.IResponsesCache;
import com.example.mfinal.mvp.model.entity.Responses;
import com.example.mfinal.mvp.model.entity.room.RoomResponses;
import com.example.mfinal.mvp.view.SearchView;
import com.example.mfinal.navigation.Screens;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.terrakok.cicerone.Router;

public class SearchPresenter extends MvpPresenter<SearchView> {

    @Inject
    IResponsesCache responsesCache;
    @Inject
    IDataSource api;
    @Inject
    Router router;
    @Inject
    Scheduler scheduler;

    private String msg;

    private static Responses resp;
    private static RoomResponses roomResp;
    private static boolean connection;

    public SearchPresenter(){
        CovidStatsApp.INSTANCE.getAppComponent().inject(this);
    }

    public String getMsg() { return msg;}

    public static Responses getResp(){return resp;}
    public static RoomResponses getRoomResp(){return roomResp;}
    public static boolean getConnection(){return connection;}


    public void afterPush(String country){
        api.getResponses(country)
                .enqueue(new Callback<Responses>() {
                    @Override
                    public void onResponse(Call<Responses> call, Response<Responses> response) {
                        if (response.body() != null){
                            // при неправильном написании прилетает нулевой массив
                            if (!response.body().getResponses().isEmpty()){
                                // по нажатию на кнопку получим действующие заражения
                                resp = response.body();
                                connection = true; // если зашли сюда, то есть соединение
                                // ложим данные в базу
                                responsesCache.putResponses(response.body())
                                        .observeOn(scheduler)
                                        .subscribe();
                                // переходим на экран ОБЩЕЙ статистики
                                router.navigateTo(new Screens.StatsScreen());
                            }
                            else { // неправильное написание страны
                                msg = "Bad way, check country";
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call call, Throwable t) {
                        connection = false;// соединения нет
                        //берем данные из базы
                        responsesCache.getAllItems(country).observeOn(scheduler).subscribe(
                                (s)->{
                                    roomResp = s;
                                    router.navigateTo(new Screens.StatsScreen());
                                    },
                                // просим проверить соединение, если страны нет в базе
                                (e)->{msg = "Check connection";});
                    }
                });
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
