package com.jesaproject.jseaproject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nell on 2018. 2. 8..
 */

public class ScheduleServices extends Service{

    ServiceThread thread;

    Activity activity;

    String[] Title1;
    String[] Context1;
    String[] Title2;
    String[] Context2;
    String[] Title3;
    String[] Context3;
    String[] Title4;
    String[] Context4;
    String[] Title5;
    String[] Context5;
    String[] Title6;
    String[] Context6;
    String[] Title7;
    String[] Context7;
    String[] Title8;
    String[] Context8;
    String[] Title9;
    String[] Context9;
    String[] Title10;
    String[] Context10;
    String[] Title11;
    String[] Context11;
    String[] Title12;
    String[] Context12;
    String[] Title13;
    String[] Context13;
    String[] Title14;
    String[] Context14;






    class myServiceHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {

            SimpleDateFormat mFormat1 = new SimpleDateFormat("MM.dd");
            SimpleDateFormat mFormat2 = new SimpleDateFormat("HH");

            long mNow = System.currentTimeMillis();
            Date mDate = new Date(mNow);

            String strDate1 = mFormat1.format(mDate);
            String strDate2 = mFormat2.format(mDate);

            if (strDate2.equals("07")) {
                for (int i = 0; i < Title2.length; i++) {
                    if (Title1.length > i) {
                        if (Title1[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context1[i]);
                        }
                    }
                    if (i < Title2.length) {
                        if (Title2[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context2[i]);
                        }
                    }

                    if (i < Title3.length) {
                        if (Title3[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context3[i]);
                        }
                    }
                    if (i < Title4.length) {
                        if (Title4[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context4[i]);
                        }
                    }
                    if (i < Title5.length) {
                        if (Title5[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context5[i]);
                        }
                    }
                    if (i < Title6.length) {
                        if (Title6[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context6[i]);
                        }
                    }
                    if (i < Title7.length) {
                        if (Title7[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context7[i]);
                        }
                    }
                    if (i < Title8.length) {
                        if (Title8[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context8[i]);
                        }
                    }
                    if (i < Title9.length) {
                        if (Title9[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context9[i]);
                        }
                    }
                    if (i < Title10.length) {
                        if (Title10[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context10[i]);
                        }
                    }
                    if (i < Title11.length) {
                        if (Title11[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context11[i]);
                        }
                    }
                    if (i < Title12.length) {
                        if (Title12[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context12[i]);
                        }
                    }
                    if (i < Title13.length) {
                        if (Title13[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context13[i]);
                        }
                    }
                    if (i < Title14.length) {
                        if (Title14[i].contains(strDate1)) {
                            createNotification notification = new createNotification(ScheduleServices.this, getApplicationContext());
                            notification.NotificationCreate("" + Context14[i]);
                        }
                    }
                }
            }

        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
        registerRestartAlarm(true);

        Resources res = getResources();
        Title1 = res.getStringArray(R.array.month_1);
        Context1 = res.getStringArray(R.array.content_1);

        Title2 = res.getStringArray(R.array.month_2);
        Context2 = res.getStringArray(R.array.content_2);

        Title3 = res.getStringArray(R.array.month_3);
        Context3 = res.getStringArray(R.array.content_3);

        Title4 = res.getStringArray(R.array.month_4);
        Context4 = res.getStringArray(R.array.content_4);

        Title5 = res.getStringArray(R.array.month_5);
        Context5 = res.getStringArray(R.array.content_5);

        Title6 = res.getStringArray(R.array.month_6);
        Context6 = res.getStringArray(R.array.content_6);

        Title7 = res.getStringArray(R.array.month_7);
        Context7 = res.getStringArray(R.array.content_7);

        Title8 = res.getStringArray(R.array.month_8);
        Context8 = res.getStringArray(R.array.content_8);

        Title9 = res.getStringArray(R.array.month_9);
        Context9 = res.getStringArray(R.array.content_9);

        Title10 = res.getStringArray(R.array.month_10);
        Context10 = res.getStringArray(R.array.content_10);

        Title11 = res.getStringArray(R.array.month_11);
        Context11 = res.getStringArray(R.array.content_11);

        Title12 = res.getStringArray(R.array.month_12);
        Context12 = res.getStringArray(R.array.content_12);

        Title13 = res.getStringArray(R.array.month_13);
        Context13 = res.getStringArray(R.array.content_13);

        Title14 = res.getStringArray(R.array.month_14);
        Context14 = res.getStringArray(R.array.content_14);


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }
    //서비스가 종료될 때 할 작업
    public void onDestroy() {
        registerRestartAlarm(false);
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    public void registerRestartAlarm(boolean isOn){
        Intent intent = new Intent(ScheduleServices.this, BootReceiver.class);
        intent.setAction(BootReceiver.ACTION_RESTART_SERVICE);
        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        if(isOn){
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 1000, 10000, sender);
        }else{
            am.cancel(sender);
        }

    }
}
