package com.aphidgrp.callrecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OutgoingCall extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null) return;
        Toast toast = Toast.makeText(context, "outgoing", Toast.LENGTH_LONG);
        toast.show();
        return;
        //Intent i = new Intent(CallRecordingService.ACTION);
        //context.startService(i);
    }
}
