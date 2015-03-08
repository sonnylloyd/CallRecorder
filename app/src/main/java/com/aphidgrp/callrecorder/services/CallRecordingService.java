package com.aphidgrp.callrecorder.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.media.MediaRecorder;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.IOException;

public class CallRecordingService extends Service implements Runnable{
    private MediaRecorder mediaRecorder;
    public static final String ACTION = "com.aphidgrp.callrecorder.services.CallRecordingService";
    private boolean rung = false;
    private String phoneNumber = "Unknown";
    private Thread aThread;
    private boolean recording = false;


    @Override
    public void onCreate()
    {
        //Toast.makeText(this, "service is running", Toast.LENGTH_LONG).show();
        //super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String state = intent.getExtras().getString("intentType");
        String phoneNumber = intent.getExtras().getString("phoneNumber");
        if(phoneNumber != null){
            this.phoneNumber = phoneNumber;
        }
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
           this.rung = true;
        }
        if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
        {
            //this.phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(this.rung){
                //aThread = new Thread(this);
                //aThread.start();
                Toast.makeText(this, "Incoming Call "+this.phoneNumber, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Outgoing Call "+this.phoneNumber, Toast.LENGTH_LONG).show();
            }
            if(!this.recording){
                //startRecording();
            }
        }
        return START_STICKY;
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
    public void onDestroy() {
    //stopRecording();
        Toast.makeText(this, "service is destroyed", Toast.LENGTH_LONG).show();
        if(this.recording){
            stopRecording();
        }
    }

    public void startRecording(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setMaxDuration(0);
        mediaRecorder.setOutputFile("filename.wav");
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
        this.recording = true;
        Toast.makeText(this, "recording", Toast.LENGTH_LONG).show();
    }

    public void stopRecording(){
        mediaRecorder.stop();
        mediaRecorder.reset();
        mediaRecorder.release();
        this.recording = false;
        Toast.makeText(this, "stop recording", Toast.LENGTH_LONG).show();
    }

}
