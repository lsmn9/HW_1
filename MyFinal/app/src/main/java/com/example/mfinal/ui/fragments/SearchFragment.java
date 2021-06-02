package com.example.mfinal.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mfinal.R;
import com.example.mfinal.mvp.presenter.SearchPresenter;
import com.example.mfinal.mvp.view.SearchView;
import com.example.mfinal.ui.BackButtonListener;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class SearchFragment extends MvpAppCompatFragment implements SearchView, BackButtonListener {

    private TextView info;
    private Button showBtn;
    private EditText search;
    private String country;

    @InjectPresenter
    SearchPresenter searchPresenter;

    public static SearchFragment getInstance(int data) {
        SearchFragment fragment = new SearchFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("key", data);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        showBtn = view.findViewById(R.id.show_button);
        info = view.findViewById(R.id.tv_info);
        search = view.findViewById(R.id.search_line);

        onShowBtnClick();

        return view;
    }


    @Override
    public void init() {

    }


    @Override
    public void onShowBtnClick() {
        showBtn.setOnClickListener(view -> {
            //берем страну и приводим к одному регистру, чтобы в базу корректно положить
            country = search.getText().toString().toUpperCase();
            searchPresenter.afterPush(country);
            //здесь косяк. Выводит предыдущее значение, я примерно понимаю почему,
            // но пока не смог исправить
            info.setText(searchPresenter.getMsg());
        });
    }

    @Override
    public boolean backPressed() {
       return searchPresenter.backPressed();
    }
}
