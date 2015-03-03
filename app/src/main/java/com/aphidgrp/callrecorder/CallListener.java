package com.aphidgrp.callrecorder;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sonny.lloyd on 03/03/2015.
 */
public class CallListener extends PhoneStateListener {
    private Context context;
    public CallListener(Context context) {
        this.context = context;
    }
    public void onCallStateChanged(int state, String incomingNumber) {
        Log.d("MyPhoneListener", state + "   incoming no:" + incomingNumber);
        if (state == 1) {
            String msg = "New Phone Call Event. Incomming Number : "+incomingNumber;
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this.context, msg, duration);
            toast.show();
        }
    }
}
