package com.example.mfinal.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mfinal.R;
import com.example.mfinal.mvp.presenter.DeathsPresenter;
import com.example.mfinal.mvp.view.DeathsView;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class DeathsFragment extends MvpAppCompatFragment implements DeathsView {

    TextView oneMPopDeaths, newDeaths, totalDeaths;

    @InjectPresenter
    DeathsPresenter deathsPresenter;

    public static DeathsFragment getInstance(int data) {
       DeathsFragment fragment = new DeathsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("key",data);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.deaths_fragment, container, false);
        totalDeaths = view.findViewById(R.id.total_deaths_amount);
        oneMPopDeaths = view.findViewById(R.id.oneMpop_deaths_amount);
        newDeaths = view.findViewById(R.id.new_deaths_amount);
        return view;
    }

    @Override
    public void init() {
        deathsPresenter.checkConnection();
        totalDeaths.setText(deathsPresenter.getTotalDeaths());
        oneMPopDeaths.setText(deathsPresenter.getOneMPopDeaths());
        newDeaths.setText(deathsPresenter.getNewDeaths());
    }
}
