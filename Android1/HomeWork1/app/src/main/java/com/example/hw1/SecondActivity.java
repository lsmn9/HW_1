package com.example.hw1;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener, Transmition {

    CheckBox humbox, presbox, windbox;
    Button btn;
    SensorManager mySM;
    Sensor humSens, presSens;
    float humValue, presValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        initToolBar();
        initItems();
        initSensors();
        btn.setOnClickListener(this);
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initItems() {
        btn = findViewById(R.id.confirmation);
        humbox = findViewById(R.id.humidity);
        presbox = findViewById(R.id.pressure);
        windbox = findViewById(R.id.wind);
    }

    private void initSensors() {
        this.mySM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        humSens = mySM.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        presSens = mySM.getDefaultSensor(Sensor.TYPE_PRESSURE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_all) {
            humbox.setChecked(true);
            presbox.setChecked(true);
            windbox.setChecked(true);
            return true;
        }
        if (id == R.id.menu_nothing) {
            humbox.setChecked(false);
            presbox.setChecked(false);
            windbox.setChecked(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        TextInputEditText et = findViewById(R.id.cityName);
        intent.putExtra(TEXT, et.getText().toString());
        intent.putExtra(humidityBox, humbox.isChecked());
        intent.putExtra(pressureBox, presbox.isChecked());
        intent.putExtra(windBox, windbox.isChecked());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (presSens != null) {
            mySM.registerListener(this, presSens, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (humSens != null) {
            mySM.registerListener(this, humSens, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySM.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            humValue = sensorEvent.values[0];
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) {
            final float PRESSURE_NORM = 1000;
            final float PRESSURE_PERMITION = 20;
            severeDeviation(PRESSURE_NORM, sensorEvent.values[0], PRESSURE_PERMITION);
            presValue = sensorEvent.values[0];
        }

    }

    private void severeDeviation(float norm, float current, float permition) {
        if (Math.abs(norm - current) <= permition) {
            {
                stopService(new Intent(SecondActivity.this, Service_Pattern.class));
            }
        }
        if (Math.abs(norm - current) > permition) {
            startService(new Intent(SecondActivity.this, Service_Pattern.class));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}