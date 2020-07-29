package com.example.hw1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        Button btn = (Button) findViewById(R.id.confirmation);
        btn.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Button button= findViewById(R.id.confirmation);
        CheckBox hum, pres, wind;
        hum = findViewById(R.id.humidity);
        pres= findViewById(R.id.pressure);
        wind = findViewById(R.id.wind);

        if (id == R.id.menu_all) {
            hum.setChecked(true);
            pres.setChecked(true);
            wind.setChecked(true);
            return true;
        }
        if (id == R.id.menu_nothing){
            hum.setChecked(false);
            pres.setChecked(false);
            wind.setChecked(false);
        return true;}
        return super.onOptionsItemSelected(item);
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