package com.example.hw1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import java.util.Objects;

public class MyReceiver extends BroadcastReceiver {

    static final String SEND_MSG = "Wrong_Town";
    private static int a = 100;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.requireNonNull(intent.getAction()).equalsIgnoreCase(SEND_MSG)) {
            String id = "id";
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context, id)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Города " + Objects.requireNonNull(intent.getExtras()).getString("intent") + " нет в базе")
                            .setContentText("Проверьте правильность написания");
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(id, "City Base",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Cities");
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(a, builder.build());
            a++;
        }
    }
}
