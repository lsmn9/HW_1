package com.example.mfinal.ui.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

import com.example.mfinal.CovidStatsApp;
import com.example.mfinal.mvp.model.network.INetworkStatus;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class AndroidNetworkStatus implements INetworkStatus {

    private static final String TAG = AndroidNetworkStatus.class.getSimpleName();

    private BehaviorSubject<Boolean> statusObject = BehaviorSubject.create();

    public AndroidNetworkStatus() {
        statusObject.onNext(false);

        ConnectivityManager connectivityManager = (ConnectivityManager) CovidStatsApp.INSTANCE
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkRequest networkRequest = new NetworkRequest.Builder().build();

        connectivityManager.registerNetworkCallback(networkRequest, new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                System.out.println("!!!!!!!!Available");
                statusObject.onNext(true);
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();

               System.out.println("!!!!!!!!UnAvailable");
                statusObject.onNext(false);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);

                System.out.println("!!!!!!!!Lost");

                statusObject.onNext(false);
            }
        });
    }

    @Override
    public Observable<Boolean> isOnline() {
        return statusObject;
    }

    @Override
    public Single<Boolean> isOnlineSingle() {
        return statusObject.first(false);
    }
}
