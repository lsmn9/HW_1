package ru.geekbrains.githubclient.ui.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.util.Log;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.network.INetworkStatus;

public class AndroidNetworkStatus implements INetworkStatus {
    private static final String TAG = AndroidNetworkStatus.class.getSimpleName();

    private BehaviorSubject<Boolean> statusObject = BehaviorSubject.create();

    public AndroidNetworkStatus() {
        statusObject.onNext(false);

        ConnectivityManager connectivityManager = (ConnectivityManager) GithubApplication.INSTANCE
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkRequest networkRequest = new NetworkRequest.Builder().build();

        connectivityManager.registerNetworkCallback(networkRequest, new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);

                if (GithubApplication.DEBUG) {
                    Log.d(TAG, "onAvailable");
                }

                statusObject.onNext(true);
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();

                if (GithubApplication.DEBUG) {
                    Log.d(TAG, "onUnavailable");
                }

                statusObject.onNext(false);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);

                if (GithubApplication.DEBUG) {
                    Log.d(TAG, "onLost");
                }

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
