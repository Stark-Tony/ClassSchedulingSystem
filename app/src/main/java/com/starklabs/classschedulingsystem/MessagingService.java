package com.starklabs.classschedulingsystem;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import java.util.Calendar;

import androidx.annotation.NonNull;
public class MessagingService extends FirebaseMessagingService
{
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getNotification()!=null)
        {
            Log.d("mylog",remoteMessage.getNotification().getTitle());
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent intent = new Intent(this,SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            Calendar calendar = Calendar.getInstance();
            PendingIntent pendingIntent = PendingIntent.getActivity(this, (int)calendar.getTimeInMillis(),intent,0);
            Notification.Builder notification = new Notification.Builder(this);
            notification.setContentTitle("Class Scheduler");
            notification.setContentText(remoteMessage.getNotification().getBody());
            notification.setContentIntent(pendingIntent).setAutoCancel(true);
            notification.setSmallIcon(R.drawable.ic_message_black_24dp);
            manager.notify(0,notification.build());
        }
        if(remoteMessage.getData().size()>0)
            Log.d("mylog", String.valueOf(remoteMessage.getData()));
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("newtoken:",s);
    }
}
