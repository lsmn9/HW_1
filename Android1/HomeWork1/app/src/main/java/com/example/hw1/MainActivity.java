package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hw1.Weather.WeatherReq;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";
    private static String LONDON_INFO = "https://en.wikipedia.org/wiki/London";
    private RetroWeather retroWeather;
    private TextView tempa, city;
    private ArrayList<String> temperature, descript;
    private SharedPreferences sPref;
    private EditText editApiKey;
    ImageView cityPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRetrofit();
        initGUI();
        initPreferences();
        initEvents();
        addForecast();
        initRecView(temperature, descript);

    }

    private void initPreferences() {
        sPref = getPreferences(MODE_PRIVATE);
        loadPreferences();
    }

    private void loadPreferences() {
        String loadedApiKey = sPref.getString("apiKey", "14c00f56e7d6dab3d5cad107267e29e4");
        editApiKey.setText(loadedApiKey);
    }


    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retroWeather = retrofit.create(RetroWeather.class);
    }

    private void requestRetrofit(String city, String keyApi) {
        retroWeather.loadWeather(city, keyApi)
                .enqueue(new Callback<WeatherReq>() {
                    @Override
                    public void onResponse(Call<WeatherReq> call, Response<WeatherReq> response) {
                        if (response.body() != null) {
                            float result = response.body().getMain().getTemp();
                            tempa.setText(Float.toString(result));
                        }

                    }

                    @Override
                    public void onFailure(Call<WeatherReq> call, Throwable t) {
                        tempa.setText("Error");
                    }
                });

    }

    private void addForecast(){
        temperature.add("+15..+17");
        temperature.add("+19..+21");
        temperature.add("+17..+19");

        descript.add("Завтра");
        descript.add("Через 1 день");
        descript.add("Через 2 дня");
    }

    private void initGUI(){

        tempa = (TextView) findViewById(R.id.Weather);
        editApiKey = findViewById(R.id.ApiKey);
        city = findViewById(R.id.City);
        cityPic = findViewById(R.id.imageView);
    }

    private void initRecView(ArrayList<String> arrayList, ArrayList<String> arrayList1) {
        RecyclerView recyclerView = findViewById(R.id.forecast);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(itemDecoration);
        MyAdapt myAdapt = new MyAdapt(arrayList, arrayList1);
        recyclerView.setAdapter(myAdapt);
    }

    private void initEvents(){

        tempa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(LONDON_INFO));
                startActivity(browser);
            }
        });

        Button refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePref();
                requestRetrofit(city.getText().toString(), editApiKey.getText().toString());
            }
        });
    }

    private void savePref(){
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("apiKey", editApiKey.getText().toString());
        editor.commit();
    }
    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        savePref();
        Toast.makeText(getApplicationContext(), "Метод onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "on_SISt.");
    }


    /*
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Метод onStart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "on_RISt.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Метод onResume()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "Метод onPause()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause");
    }



    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "Метод onStop()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "Метод onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), this + "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy");
    }

*/

}

