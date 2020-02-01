package com.jesaproject.jseaproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by nell on 2018. 2. 8..
 */

public class BootReceiver extends BroadcastReceiver {


    static public final String ACTION_RESTART_SERVICE = "RestartReceiver.restart";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // 전달된 값이 '부팅완료' 인 경우에만 동작 하도록 조건문을 설정 해줍니다.
        if (action.equals("android.intent.action.BOOT_COMPLETED")) {
            Intent i = new Intent(context, ScheduleServices.class);
            context.startService(i);
        }
        else if(action.equals(ACTION_RESTART_SERVICE)){
            Intent i = new Intent(context, ScheduleServices.class);
            context.startService(i);
        }
    }
}
