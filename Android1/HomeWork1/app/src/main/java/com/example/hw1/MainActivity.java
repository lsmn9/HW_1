package com.example.hw1;


import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.hw1.MyReceiver.SEND_MSG;


public class MainActivity extends AppCompatActivity implements Transmition {

    private static final int PERMISSION_REQUEST_CODE = 10;

    private final float KEL_TO_CEL = 273f; // kelvin to cels
    private static String INFO = "https://en.wikipedia.org/wiki/";
    private RetroWeather retroWeather;
    private TextView tempa, town, humidity, wind, pressure;
    private SharedPreferences sPref;
    private EditText editApiKey;
    private Geocoder geocoder;
    private Double lat, lng;
    private MyReceiver myReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geocoder = new Geocoder(this, Locale.getDefault());
        initGUI();
        initReceiver();
        initPreferences();
        initEvents();
        initRetrofit();
        requestPemissions();
    }

    private void initGUI() {
        tempa = findViewById(R.id.Weather);
        humidity = findViewById(R.id.humidity);
        wind = findViewById(R.id.wind);
        pressure = findViewById(R.id.pressure);
        editApiKey = findViewById(R.id.ApiKey);
        town = findViewById(R.id.City);
        town.setText(getIntent().getExtras().getString(TEXT));
    }

    private void initReceiver() {
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, new IntentFilter(SEND_MSG));
    }

    private void initPreferences() {
        sPref = getPreferences(MODE_PRIVATE);
        loadPreferences();
    }

    private void loadPreferences() {
        String loadedApiKey = sPref.getString("apiKey", "14c00f56e7d6dab3d5cad107267e29e4");
        editApiKey.setText(loadedApiKey);
    }

    private void initEvents() {
        town.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button mytown = findViewById(R.id.mytown);
        mytown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePref();
                requestRetrofit(myTownName(), editApiKey.getText().toString());
                town.setText(myTownName());
            }
        });

        Button info = findViewById(R.id.information);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(INFO + town.getText().toString()));
                startActivity(browser);
            }
        });
        Button refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePref();
                requestRetrofit(town.getText().toString(), editApiKey.getText().toString());
            }
        });
    }

    private void requestRetrofit(String city, String keyApi) {
        retroWeather.loadWeather(city, keyApi)
                .enqueue(new Callback<WeatherReq>() {
                    @Override
                    public void onResponse(Call<WeatherReq> call, Response<WeatherReq> response) {
                        if (response.body() != null) {
                            tempa.setText(Math.round(response.body().getMain().getTemp() - KEL_TO_CEL) + " C");
                            humidity.setText(additions(humidityBox,
                                    String.valueOf(response.body().getMain().getHumidity()), "%"));
                            wind.setText(additions(windBox,
                                    String.valueOf(response.body().getWind().getSpeed()), "knt"));
                            pressure.setText(additions(pressureBox,
                                    String.valueOf(response.body().getMain().getPressure()), "GPa"));
                        } else {
                            String msg = town.getText().toString();
                            Intent intent = new Intent();
                            intent.setAction(SEND_MSG);
                            intent.putExtra("intent", msg);
                            sendBroadcast(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherReq> call, Throwable t) {
                        tempa.setText("Error");
                    }
                });
    }

    private String additions(String box, String data, String units) {
        if (Objects.requireNonNull(getIntent().getExtras()).getBoolean(box)) {
            return box + ": " + data + " " + units + "\n";
        } else return "";
    }

    private String myTownName() {
        String mytown = "";

        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);

            if (addresses != null) {
                if (addresses.get(0).getLocality() == null) {
                    mytown = addresses.get(0).toString();
                } else mytown = addresses.get(0).getLocality();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mytown;
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retroWeather = retrofit.create(RetroWeather.class);
    }

    private void requestPemissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocation();
        } else {
            requestLocationPermissions();
        }
    }

    private void requestLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        String provider = locationManager.getBestProvider(criteria, true);
        if (provider != null) {

            locationManager.requestLocationUpdates(provider, 10000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    lat = location.getLatitude();
                    lng = location.getLongitude();

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }
            });
        }
    }

    private void requestLocationPermissions() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 2 &&
                    (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                requestLocation();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        savePref();
    }

    private void savePref() {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("apiKey", editApiKey.getText().toString());
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}

