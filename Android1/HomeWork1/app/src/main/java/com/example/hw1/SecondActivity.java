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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    CheckBox humbox, presbox, windbox;
    SensorManager mySM;
    Sensor humSens, presSens;
    TextView humData, presData;
    float humValue, presValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        Button btn = findViewById(R.id.confirmation);
        btn.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        humbox = findViewById(R.id.humidity);
        presbox= findViewById(R.id.pressure);
        windbox = findViewById(R.id.wind);

        mySM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        humSens = mySM.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        humData =  findViewById(R.id.hum_TxtV);
        presSens = mySM.getDefaultSensor(Sensor.TYPE_PRESSURE);
        presData = findViewById(R.id.pres_TxtV);
    }

    @Override
    protected void onResume() {
       super.onResume();
       // регистрируем слушатели
       if (presSens != null){ // на случай, если не предусмотрен
           mySM.registerListener(this, presSens, SensorManager.SENSOR_DELAY_NORMAL);
       } else {
           presData.setText("No_such_sensor");}

       if (humSens != null){
           mySM.registerListener(this, humSens, SensorManager.SENSOR_DELAY_NORMAL);
       } else {
           humData.setText("No_such_sensor");}
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySM.unregisterListener(this); // разрегистриуем
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Button button = findViewById(R.id.confirmation);
        if (id == R.id.menu_all) {
            humbox.setChecked(true);
            presbox.setChecked(true);
            windbox.setChecked(true);
            return true;
        }
        if (id == R.id.menu_nothing){
            humbox.setChecked(false);
            presbox.setChecked(false);
            windbox.setChecked(false);
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

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        // если сенсор определенного типа записываем данные
        if (sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY)
        {humValue = sensorEvent.values[0];}
        if (sensorEvent.sensor.getType()==Sensor.TYPE_PRESSURE)
        {presValue = sensorEvent.values[0];}
        // небольшой метод для вывода
        boxIsChoosen(humbox, String.valueOf(humValue), humData);
        boxIsChoosen(presbox,String.valueOf(presValue), presData);

    }

    private void boxIsChoosen (CheckBox checkBox, String string, TextView textView){
      // при выбранном чекбоксе показываем данные
        if (checkBox.isChecked()){
            textView.setVisibility(View.VISIBLE);
            textView.setText(string);
        }
        else { // при невыбранном скрываем поле
            textView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}