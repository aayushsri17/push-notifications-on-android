package com.example.aayushsrivastava.project12.main;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.aayushsrivastava.project12.R;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Aayush Srivastava on 04-07-2016.
 */
public class GCMPushReceiverService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {

        String message = data.getString("message");
        sendNotification(message);

    }

    private void sendNotification(String messege){

        Intent intent = new Intent(this,MainActivity2.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int reguestCode = 0; //your request code
        PendingIntent pendingIntent= PendingIntent.getActivity(this,reguestCode,intent,PendingIntent.FLAG_ONE_SHOT);
        //setup notification
        //sound

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder setBuilder =new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentText("My GCM Text")
                .setContentText(messege)
                .setAutoCancel(true).setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder noBuilder =new NotificationCompat.Builder(this);
        notificationManager.notify( 0 , noBuilder.build());
        // 0 is the id of notification
    }
}
