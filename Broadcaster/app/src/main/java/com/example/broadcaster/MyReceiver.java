package com.example.broadcaster;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "id")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Внимание!!!")
                        .setContentText("Низкий заряд батареи")
                        .setPriority(0);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(100, builder.build());


    }
}
