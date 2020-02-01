package com.jesaproject.jseaproject;

/**
 * Created by nell on 2018. 1. 24..
 */

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Nell on 2016-04-20.
 */
public class BackPressCloseHandler {

    private long BackKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressd() {
        if (System.currentTimeMillis() > BackKeyPressedTime + 2000) {
            BackKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= BackKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
