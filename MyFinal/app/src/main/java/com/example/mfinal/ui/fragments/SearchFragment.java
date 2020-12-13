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

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class SearchFragment extends MvpAppCompatFragment implements SearchView {

    private View view;
    private TextView tv;
    private Button btn;
    private EditText editText;;
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
        view = inflater.inflate(R.layout.search_fragment, container, false);
        tv = view.findViewById(R.id.tv);
        btn = view.findViewById(R.id.button);
        editText = view.findViewById(R.id.et);
        onBtnClick();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void init() {

    }

    @Override
    public void onBtnClick() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                country = editText.getText().toString();
                System.out.println(country);
                searchPresenter.afterPush(country);
                tv.setText(searchPresenter.getActCases());

            }
        });

    }
}
