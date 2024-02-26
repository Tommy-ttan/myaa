package com.tommyhumaxcar.myaa.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tommyhumaxcar.myaa.common.AppInfo;

public class BroadcastReceiverHandler extends BroadcastReceiver {
    private static String TAG = AppInfo.APP + "BroadcastReceiverHandler";
    @Override
    public void onReceive(Context context, Intent intent) {
        String act = intent.getAction();
        Log.d(TAG, "onReceive: BroadcastReceiverHandler action: " + act);

        if (act.equals("ACTION_AA_SEND_NOTI")) {
            NotiSender.getInstance().onReceiveBroadcast(act);
        } else {
            Log.d(TAG, "do nothing!!!");
        }
    }
}
