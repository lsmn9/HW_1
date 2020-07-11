package com.example.hw1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragmet1 extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment1, container, false);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            view.setVisibility(View.INVISIBLE);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            view.setVisibility(View.VISIBLE);
    }
}
