package com.tommyhumaxcar.myaa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tommyhumaxcar.myaa.R;
import com.tommyhumaxcar.myaa.common.AppInfo;
import com.tommyhumaxcar.myaa.common.UiStyle;
import com.tommyhumaxcar.myaa.controller.NotiSender;
import com.tommyhumaxcar.myaa.controller.SetRank;

public class MainActivity extends AppCompatActivity {
    private final String TAG = AppInfo.APP + "MainActivity";
    LinearLayout mMainLayout = null;
    Context mContext = this;
    View.OnClickListener defaultButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btn = (Button)v;
            Log.d(TAG, "onClick: Clicked item " + btn.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppInfo.getInstance().setApplicationContext(this.getApplicationContext());
        AppInfo.getInstance().setWindowManager(this.getWindowManager());

        mMainLayout = (LinearLayout) findViewById(R.id.main_layout);
        generateUI();
    }

    @Override
    protected void onDestroy() {super.onDestroy();}

    private void generateUI() {
        //Header
        addTextView("This is My AA", UiStyle.STYLE_2, UiStyle.LAYOUT_STYLE_3);

        // Header
        addTextView("Device Information", UiStyle.STYLE_2, UiStyle.LAYOUT_STYLE_2);
        // Device Information
        addTextView("Package Name: "+ mContext.getPackageName(),
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
        addTextView("Android OS: "+ System.getProperty("os.version"),
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);

        // Header
        addTextView("Test button", UiStyle.STYLE_2, UiStyle.LAYOUT_STYLE_2);
        // Test button
        addButton("Send Noti", NotiSender.getInstance().onButtonClick(),
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
        addButton("Set Rank", SetRank.getInstance().onButtonClick(),
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);

        // Header
        addTextView("Broadcast receiver", UiStyle.STYLE_2, UiStyle.LAYOUT_STYLE_2);
        // Test Broadcast on-off
        addTextView("Use command below to send Broadcast: " +
                        "adb shell \"am broadcast -a ACTION_AA_SEND_NOTI -n com.tommyhumaxcar.myaa/.controller.BroadcastReceiverHandler --es extra_data \"Hello from adb!\\",
                UiStyle.STYLE_3, UiStyle.LAYOUT_STYLE_3);
        addTextView("Register Broadcast receiver",
                UiStyle.STYLE_3, UiStyle.LAYOUT_STYLE_1);
        addCheckBox(AppInfo.ACTION_AA_SEND_NOTI,
                NotiSender.getInstance().onCheck(),
                UiStyle.STYLE_1, UiStyle.LAYOUT_STYLE_1);
    }

    private void addTextView(String text, int item_style, int layout_style) {
        TextView text_view = UiStyle.getInstance().newTextViewStyle(item_style, layout_style);
        text_view.setText(text);
        mMainLayout.addView(text_view);
    }

    private void addButton(String content, View.OnClickListener event, int item_style, int layout_style) {
        Button button = UiStyle.getInstance().newButtonStyle(UiStyle.STYLE_4, UiStyle.LAYOUT_STYLE_2);
        button.setText(content);
        if(event==null) {
            button.setOnClickListener(defaultButtonClicked);
        }
        button.setOnClickListener(event);
        mMainLayout.addView(button);
    }

    private void addCheckBox(String content, CompoundButton.OnCheckedChangeListener onCheck, int item_style, int layout_style) {
        CheckBox check_box = UiStyle.getInstance().newCheckBoxStyle(item_style, layout_style);
        check_box.setText(content);
        check_box.setOnCheckedChangeListener(onCheck);
        mMainLayout.addView(check_box);
    }
}