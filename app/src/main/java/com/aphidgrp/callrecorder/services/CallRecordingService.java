package com.aphidgrp.callrecorder.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaRecorder;

import java.io.IOException;

public class CallRecordingService extends Service implements Runnable{
    private MediaRecorder mediaRecorder;
    public static final String ACTION = "com.aphidgrp.callrecorder.services.CallRecordingService";

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
        startRecording();
    }

    @Override
    public void onDestroy() {stopRecording();}

    public void startRecording(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setMaxDuration(0);
        mediaRecorder.setOutputFile("filename");
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
    }

    public void stopRecording(){
        mediaRecorder.stop();
        mediaRecorder.reset();
        mediaRecorder.release();
    }

}
