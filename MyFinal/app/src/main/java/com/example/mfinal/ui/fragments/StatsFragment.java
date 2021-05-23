package com.example.mfinal.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mfinal.R;
import com.example.mfinal.mvp.presenter.StatsPresenter;
import com.example.mfinal.mvp.view.StatsView;
import com.example.mfinal.ui.BackButtonListener;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class StatsFragment extends MvpAppCompatFragment implements StatsView, BackButtonListener {


    private TextView casesCounter;
    private TextView deathCounter;
    private TextView country;

    @InjectPresenter
    StatsPresenter actStatsPresenter;

    public static StatsFragment getInstance(int data) {
        StatsFragment fragment = new StatsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("key", data);

        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_fragment, container, false);
        casesCounter = view.findViewById(R.id.cases_amount);
        deathCounter = view.findViewById(R.id.deaths_amount);
        country = view.findViewById(R.id.country);
        onDeathClick();
        onCasesClick();
        return view;

    }


    @Override
    public void init() {
        actStatsPresenter.checkConnection();
        casesCounter.setText(actStatsPresenter.getTotalCases());
        deathCounter.setText(actStatsPresenter.getTotalDeaths());
        country.setText(actStatsPresenter.getCurrentCountry());
    }

    @Override
    public void onCasesClick() {
        casesCounter.setOnClickListener(view -> actStatsPresenter.casesPushed());
    }

    @Override
    public void onDeathClick() {
        deathCounter.setOnClickListener(view -> actStatsPresenter.deathsPushed());
    }

    @Override
    public boolean backPressed() {
        return actStatsPresenter.backPressed();
    }
}