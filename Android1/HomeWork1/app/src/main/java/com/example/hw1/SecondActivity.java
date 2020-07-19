package com.example.hw1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        Button btn = (Button) findViewById(R.id.confirmation);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        TextInputEditText et = findViewById(R.id.cityName);
        if (et.getText().toString().equals("London"))
        {startActivity(intent);}
        else Snackbar.make (view, "Wrong City", Snackbar.LENGTH_SHORT).
                setAction("Action", null).show();

    }


}