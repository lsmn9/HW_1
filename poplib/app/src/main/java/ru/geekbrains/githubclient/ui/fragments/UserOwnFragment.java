package ru.geekbrains.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.R;
import ru.geekbrains.githubclient.mvp.model.entity.room.Database;
import ru.geekbrains.githubclient.mvp.model.repo.IRepoRepo;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitRepoRepo;
import ru.geekbrains.githubclient.mvp.presenter.UserOwnPresenter;
import ru.geekbrains.githubclient.mvp.view.UserOwnView;
import ru.geekbrains.githubclient.ui.BackButtonListener;
import ru.geekbrains.githubclient.ui.adapter.UserOwnAdapter;
import ru.geekbrains.githubclient.ui.network.AndroidNetworkStatus;
import ru.terrakok.cicerone.Router;


public class UserOwnFragment extends MvpAppCompatFragment implements BackButtonListener, UserOwnView {

    private TextView head;
    private RecyclerView recyclerView;
    private UserOwnAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private View view;


    @InjectPresenter
    UserOwnPresenter userOwnPresenter;

    @ProvidePresenter
    UserOwnPresenter provideUserOwnPresenter(){
        IRepoRepo repoRepo = new RetrofitRepoRepo(GithubApplication.INSTANCE.getApi(),
                                                    new AndroidNetworkStatus(),
                                                    Database.getInstance());
        Router router = GithubApplication.getApplication().getRouter();
        return new UserOwnPresenter(AndroidSchedulers.mainThread(), repoRepo, router);
    }

    public static UserOwnFragment getInstance(int data) {
        UserOwnFragment fragment = new UserOwnFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("key", data);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_own_screen, container, false);
        recyclerView = view.findViewById(R.id.rv_repos);
        head = view.findViewById(R.id.tv_head);
        return view;
    }

    @Override
    public boolean backPressed() {
        return userOwnPresenter.backPressed();
    }

    @Override
    public void init() {
        head.setText(userOwnPresenter.getLogin());
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new UserOwnAdapter(userOwnPresenter.getUserOwnListPresenter());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }
}
