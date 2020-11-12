package ru.geekbrains.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.geekbrains.githubclient.R;
import ru.geekbrains.githubclient.mvp.presenter.UserOwnPresenter;
import ru.geekbrains.githubclient.mvp.view.UserOwnView;
import ru.geekbrains.githubclient.ui.BackButtonListener;

public class UserOwnFragment extends MvpAppCompatFragment implements BackButtonListener, UserOwnView {

private View view;
private TextView login;

    @InjectPresenter
    UserOwnPresenter userPresenter;

    public static UserOwnFragment getInstance(int data) {
        UserOwnFragment fragment = new UserOwnFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("key", data);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_own_screen, container, false);
        login = view.findViewById(R.id.own_login);
        login.setText(userPresenter.getLogin());
        return view;
    }

    @Override
    public boolean backPressed() {
        return userPresenter.backPressed();
    }

    @Override
    public void init() {
    //на будущее
    }
}
