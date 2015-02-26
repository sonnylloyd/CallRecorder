package com.aphidgrp.callrecorder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaRecorder;

public class CallRecordingService extends Service implements Runnable{
    private MediaRecorder mediaRecorder;
    //private static ConfigurationManager configurationManager;

    public static final String ACTION = "com.aphidgrp.callrecorder.CallRecordingService";

    public CallRecordingService() {

    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Thread aThread = new Thread(this);
        aThread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void run() {
        //start recording
        mediaRecorder = new MediaRecorder();
        //mediaRecorder.setAudioSource(this.configurationManager.getAudioSource());


    }
}
