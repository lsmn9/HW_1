package com.example.broadcaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String id = "MyID";
    private MyReceiver myReceiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.editTextTextPersonName);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, id)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("Внимание!!!")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                if (editText.toString().isEmpty()) {
                    builder.setContentText("Пример работы");
                } else builder.setContentText(editText.getText().toString());

                NotificationManagerCompat notificationManager;
                notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(
                        1000,
                        builder.build());


            }
        });
        registerReceiver(myReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}