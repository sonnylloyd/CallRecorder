package com.aphidgrp.callrecorder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaRecorder;

public class CallRecordingService extends Service{
    public static final String ACTION = "com.aphidgrp.callrecorder.CallRecordingService";

    public CallRecordingService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
