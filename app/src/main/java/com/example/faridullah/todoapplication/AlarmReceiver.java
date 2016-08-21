package com.example.faridullah.todoapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Farid ullah on 8/1/2016.
 */
public class AlarmReceiver extends BroadcastReceiver{

    private static int  numNotification = 0;
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Alarm is ringing", Toast.LENGTH_SHORT).show();
        Log.d("AlarmReceiver", "I am here");
//        NotificationCompat.Builder notification = new NotificationCompat.Builder(context);
//
//        notification.setSmallIcon(R.drawable.notification_icon);
//        notification.setContentTitle(intent.getStringExtra("TASK_TITLE"));
//        notification.setContentText( "This has been completed");
//        notification.setTicker("TodoApplication Alert");
//        notification.setNumber(++numNotification);
//
//        Intent resultantActivity = new Intent(context,MainActivity.class);
//        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
//        taskStackBuilder.addParentStack(MainActivity.class);
//        taskStackBuilder.addNextIntent(resultantActivity);
//        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setContentIntent(pendingIntent);
//
//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        notification.setSound(uri);
//
//
//        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//
//        // notificationID allows you to update the notification later on.
//        mNotificationManager.notify(1,notification.build());
    }
}
