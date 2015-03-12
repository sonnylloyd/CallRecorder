package com.aphidgrp.callrecorder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.aphidgrp.callrecorder.services.CallRecordingService;

public class NewCall extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null) return;
        Intent i = new Intent(context,CallRecordingService.class);
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            i.putExtra("intentType","OUTGOING");
            i.putExtra("phoneNumber",intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER));
            context.startService(i);
        }
    }
}
