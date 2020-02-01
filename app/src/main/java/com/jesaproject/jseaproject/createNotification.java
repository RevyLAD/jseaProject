package com.jesaproject.jseaproject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by user on 2018-02-01.
 */


public class createNotification{

    private NotificationManager notiManager;


    Activity activity;

    Context context;

    Service service;

    FirebaseMessagingService firebaseMessagingService;
    public createNotification(Service service, Context context) {

        this.service = service;
        this.context = context;
    }

    public createNotification(FirebaseMessagingService firebaseMessagingService, Context context)
    {
        this.firebaseMessagingService = firebaseMessagingService;
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void NotificationCreate(String message) {
        NotificationManager mNotificationManager = (NotificationManager) service.getSystemService(Context.NOTIFICATION_SERVICE);

// The id of the channel.
        String id = "my_channel_01";

// The user-visible name of the channel.
        CharSequence name = "JSEA_CHANNEL";

// The user-visible description of the channel.
        String description = "JSEA_DESCRIPTTION";

        int importance = NotificationManager.IMPORTANCE_LOW;

        NotificationChannel mChannel = new NotificationChannel(id, name, importance);

// Configure the notification channel.
        mChannel.setDescription(description);
        mChannel.enableLights(true);

// Sets the notification light color for notifications posted to this
// channel, if the device supports this feature.
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        mNotificationManager.createNotificationChannel(mChannel);
// Sets an ID for the notification, so it can be updated.
        int notifyID = 1;

// The id of the channel.
        String CHANNEL_ID = "my_channel_01";

// Create a notification and set the notification channel.\
        Notification notification = new Notification.Builder(service)
                .setContentTitle("강원대학교 학사정보 커뮤니티")
                .setContentText(message)
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setChannelId(CHANNEL_ID)
                .build();

// Issue the notification.
        mNotificationManager.notify(0, notification);


    }

}
