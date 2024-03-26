package com.tommyhumaxcar.myaa.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tommyhumaxcar.myaa.common.AppInfo;

public class SetRank {
    private final String TAG = AppInfo.APP + "SetRank";
    private String pkgAa = "com.tommyhumaxcar.myaa";
    private String pkgPhone = "com.tommyhumaxcar.myphone";
    private static SetRank mInstance = null;
    private SetRank() {};
    public static SetRank getInstance() {
        if (mInstance == null) {
            mInstance = new SetRank();
        }
        return mInstance;
    }

    private void sendBroadcastLock() {
        Intent i = new Intent();
        i.setAction("ACTION_SET_RANK");
        Bundle b = new Bundle();
        // For lock
        String data[] = {"LOCK", pkgAa, pkgPhone};
        b.putStringArray(".EXTRA_KEY", data);
        i.putExtras(b);
        AppInfo.getInstance().getApplicationContext().sendBroadcast(i);
    }

    private void sendBroadcastUnLock() {
        Intent i = new Intent();
        i.setAction("ACTION_SET_RANK");
        Bundle b = new Bundle();
        // For unlock
        String data[] = {"UNLOCK", pkgAa, pkgAa};
        b.putStringArray(".EXTRA_KEY", data);
        i.putExtras(b);
        AppInfo.getInstance().getApplicationContext().sendBroadcast(i);
    }

    public View.OnClickListener onButtonLockClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button)v;
                Log.d(TAG, "ButtonClick: " + btn.getText().toString());
                sendBroadcastLock();
            }
        };
    }

    public View.OnClickListener onButtonUnLockClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button)v;
                Log.d(TAG, "ButtonClick: " + btn.getText().toString());
                sendBroadcastUnLock();
            }
        };
    }
}
