package com.aphidgrp.callrecorder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import com.aphidgrp.callrecorder.services.CallRecordingService;

public class CallStatus extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null) return;
        Bundle extras = intent.getExtras();
        String state = extras.getString(TelephonyManager.EXTRA_STATE);
        Intent i = new Intent(context,CallRecordingService.class);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
                i.putExtra("intentType", "INCOMING");
                i.putExtra("phoneNumber", extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER));
                context.startService(i);
        }
        if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
        {
                i.putExtra("intentType", "ANSWERED");
                context.startService(i);
        }
        if(state.equals(TelephonyManager.EXTRA_STATE_IDLE))
        {
              i.putExtra("intentType", "HUNGUP");
              context.stopService(i);
        }
    }
}
