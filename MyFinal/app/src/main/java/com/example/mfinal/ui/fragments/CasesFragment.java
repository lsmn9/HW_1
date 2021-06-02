package com.example.mfinal.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mfinal.R;
import com.example.mfinal.mvp.presenter.CasesPresenter;
import com.example.mfinal.mvp.view.CasesView;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class CasesFragment extends MvpAppCompatFragment implements CasesView {

    TextView totalCases, newCases, activeCases, recoveredCases, oneMPopCases, criticalCases;

    @InjectPresenter
    CasesPresenter casesPresenter;


    public static CasesFragment getInstance(int data) {
        CasesFragment fragment = new CasesFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("key", data);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cases_fragment, container, false);
        totalCases = view.findViewById(R.id.total_cases_amount);
        newCases = view.findViewById(R.id.new_cases_amount);
        activeCases = view.findViewById(R.id.active_cases_amount);
        recoveredCases = view.findViewById(R.id.recovered_cases_amount);
        oneMPopCases = view.findViewById(R.id.oneMpop_cases_amount);
        criticalCases = view.findViewById(R.id.critical_cases_amount);
        return view;
    }

    @Override
    public void init() {
        casesPresenter.checkConnection();
        totalCases.setText(casesPresenter.getTotalCases());
        newCases.setText(casesPresenter.getNewCases());
        activeCases.setText(casesPresenter.getActiveCases());
        recoveredCases.setText(casesPresenter.getRecoveredCases());
        oneMPopCases.setText(casesPresenter.getOneMPopCases());
        criticalCases.setText(casesPresenter.getCriticalCases());
    }
}

