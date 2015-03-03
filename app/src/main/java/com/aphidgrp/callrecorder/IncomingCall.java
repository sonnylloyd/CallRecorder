package com.aphidgrp.callrecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class IncomingCall extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null) return;
        try {
            TelephonyManager phoneManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            CallListener callListener = new CallListener(context);
            phoneManager.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
        } catch (Exception e) {
            Log.e("Phone Receive Error", " " + e);
        }
        //String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Toast toast = Toast.makeText(context, "incoming", Toast.LENGTH_LONG);
        toast.show();
        return;
        //Intent i = new Intent(CallRecordingService.ACTION);
        //context.startService(i);
    }
}
