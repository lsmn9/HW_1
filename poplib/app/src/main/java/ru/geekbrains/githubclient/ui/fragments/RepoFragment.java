package ru.geekbrains.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.geekbrains.githubclient.R;
import ru.geekbrains.githubclient.mvp.presenter.RepoPresenter;
import ru.geekbrains.githubclient.mvp.view.RepoView;
import ru.geekbrains.githubclient.ui.BackButtonListener;
import ru.geekbrains.githubclient.ui.adapter.UserOwnAdapter;


public class RepoFragment extends MvpAppCompatFragment implements RepoView, BackButtonListener {

    private TextView head, counter;
    private View view;

    @InjectPresenter
    RepoPresenter repoPresenter;


    public static RepoFragment getInstance(int data) {
        RepoFragment fragment = new RepoFragment();

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
        view = inflater.inflate(R.layout.user_repo_forks, container, false);
        head = view.findViewById(R.id.tv_fork_head);
        counter = view.findViewById(R.id.forks_counter);
        return view;
    }

    @Override
    public boolean backPressed() {
        return repoPresenter.backPressed();
    }

    @Override
    public void init() {
        head.setText(repoPresenter.getRepoName());
        counter.setText(( String.valueOf(repoPresenter.getForksCount())));
    }
}
