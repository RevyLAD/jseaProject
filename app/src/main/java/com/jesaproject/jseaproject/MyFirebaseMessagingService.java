package com.jesaproject.jseaproject;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by nell on 2018. 2. 22..
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String FILENAME = "check.txt";
    String filecheck;
    private NotificationManager notifManager;

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String from = remoteMessage.getFrom();

        Map<String, String> data = remoteMessage.getData();

        String contents = data.get("contents");


        //추가한것

        fileRead();



        if(filecheck.equals("1")) {
            if(contents != null)
                sendNotification("b");
            else
                sendNotification(remoteMessage.getData().get("message"));
        }

    }

    private void sendNotification(String messageBody) {

        if(messageBody.equals("a"))
        {
            messageBody = "새 게시글이 등록 되었습니다. 클릭해서 확인해 보세요!";
        }
        if(messageBody.equals("b"))
        {
            messageBody = "회원님이 올린 글에 새 댓글이 달렸습니다.";
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_logo)
                        .setContentTitle("강원대학교 학사정보 커뮤니티")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(1 /* ID of notification */, notificationBuilder.build());
    }


    private void fileRead()
    {
        try {
            FileInputStream fileInputStream = openFileInput(FILENAME);
            byte[] buffer = new byte[fileInputStream.available()];

            fileInputStream.read(buffer);
            filecheck = new String(buffer);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}