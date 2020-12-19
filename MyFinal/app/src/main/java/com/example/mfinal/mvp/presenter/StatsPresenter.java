package com.example.mfinal.mvp.presenter;

import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.model.entity.Responses;
import com.example.mfinal.mvp.model.entity.room.RoomResponses;
import com.example.mfinal.mvp.view.StatsView;
import com.example.mfinal.navigation.Screens;

import javax.inject.Inject;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class StatsPresenter extends MvpPresenter<StatsView> {

    private static Responses response;
    private static RoomResponses roomResponses;
    private static boolean connection;
    private String totalCases, totalDeaths, currentCountry;


    @Inject
    Router router;

    public StatsPresenter(){
        CovidStatsApp.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public String getTotalCases() { return totalCases;} //все заражения
    public String getTotalDeaths() { return totalDeaths;}//все смерти
    public String getCurrentCountry() { return currentCountry;}

    public  static Responses getResponse() { return response;}
    public  static RoomResponses getRoomResponses() { return roomResponses;}
    public  static boolean isConnection() { return connection; }

    // метод после нажатия на кол-во смертей - выводит подробную статиску
    public void deathsPushed (){
        router.navigateTo(new Screens.DeathsScreen());
    }

    // то же для заражений
    public void casesPushed(){
        router.navigateTo(new Screens.CasesScreen());
    }

    //проверяем наличие соединения - смотрим откуда брать данные
    public void checkConnection(){

        connection = SearchPresenter.getConnection();

        if(connection){
            response = SearchPresenter.getResp();
            totalCases = String.valueOf(response.getResponses().get(0).getCases().getTotalCases());
            totalDeaths = String.valueOf(response.getResponses().get(0).getDeaths().getTotalDeath());
            currentCountry = String.valueOf(response.getResponses().get(0).getCountry());

        } else {
            roomResponses = SearchPresenter.getRoomResp();
            // если данные офлайн, то под страной указываем актуальную дату для данных
            currentCountry = roomResponses.getCountry() + "\n(" + roomResponses.getDay()+")";
            totalCases = String.valueOf(roomResponses.getCases().getTotalCases());
            totalDeaths = String.valueOf(roomResponses.getDeaths().getTotalDeath());
        }
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
