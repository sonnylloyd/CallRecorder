package com.aphidgrp.callrecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OutgoingCall extends BroadcastReceiver {
    public OutgoingCall() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null) return;
        Intent i = new Intent(CallRecordingService.ACTION);
        context.startService(i);
    }
}
