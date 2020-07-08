package com.example.hw1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        EditText et = (EditText) findViewById(R.id.cityName);
        if (et.getText().toString().equals("London"))
        {startActivity(intent);}
        else Toast.makeText(getApplicationContext(), "Wrong City", Toast.LENGTH_SHORT).show();

    }


}