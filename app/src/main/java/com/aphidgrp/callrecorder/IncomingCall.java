package com.aphidgrp.callrecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class IncomingCall extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null) return;
        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Toast toast = Toast.makeText(context, phoneNumber, Toast.LENGTH_LONG);
        toast.show();
        Intent i = new Intent(CallRecordingService.ACTION);
        context.startService(i);
    }
}
