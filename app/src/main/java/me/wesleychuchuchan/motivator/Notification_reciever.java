package me.wesleychuchuchan.motivator;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification_reciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Cursor res = MainActivity.DB.getQuotes();
        if(res.getCount()>0){
            res.moveToFirst();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyUser")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Your Daily Quote")
                    .setContentText(res.getString(0))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            notificationManager.notify(200,builder.build());
        }


    }
}

