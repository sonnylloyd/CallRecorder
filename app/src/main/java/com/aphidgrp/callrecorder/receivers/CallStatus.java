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
        Intent i = new Intent(CallRecordingService.ACTION);

        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            Toast.makeText(context, "Phone Is Ringing" + extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER), Toast.LENGTH_LONG).show();
            //check if the service has started if not create the service and wait
            //context.startService(i);
        }

        if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
        {
            Toast.makeText(context, "Phone has been picked up", Toast.LENGTH_LONG).show();
            //check if the service has started if it has then its incoming if not then start outgoing
        }

        if(state.equals(TelephonyManager.EXTRA_STATE_IDLE))
        {
            Toast.makeText(context, "Phone has been hung up", Toast.LENGTH_LONG).show();
            //check if service is running if so stop recording
            //stopService(new Intent(CallRecordingService.ACTION));
            //context.stopService(i);
        }
    }
}
