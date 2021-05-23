package com.example.mfinal.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mfinal.R;
import com.example.mfinal.mvp.presenter.ActualStatsPresenter;
import com.example.mfinal.mvp.view.ActStatsView;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ActStatsFragment extends MvpAppCompatFragment implements ActStatsView {

    private TextView head, counter;
    private View view;

    @InjectPresenter
    ActualStatsPresenter actStatsPresenter;

    public static ActStatsFragment getInstance(int data) {
        ActStatsFragment fragment = new ActStatsFragment();

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
        view = inflater.inflate(R.layout.stats_fragment, container, false);
        head = view.findViewById(R.id.act_cases_label);
        counter = view.findViewById(R.id.cases);
        return view;
    }


    @Override
    public void init() {
        counter.setText(actStatsPresenter.getActualCases());
    }
}
