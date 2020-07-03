package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        String instanceState;
        if (savedInstanceState == null){
            instanceState = "Первый запуск!";
        }
        else{
            instanceState = "Повторный запуск!";
        }
        Toast.makeText(getApplicationContext(), instanceState + "Метод onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate" );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Метод onStart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart" );
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "on_RISt." );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Метод onResume()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "Метод onPause()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause" );
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "Метод onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "on_SISt." );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "Метод onStop()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop" );
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "Метод onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onRestart" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), this + "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy" );
    }

}

