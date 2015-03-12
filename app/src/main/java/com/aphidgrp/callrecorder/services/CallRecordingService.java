package com.aphidgrp.callrecorder.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.media.MediaRecorder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class CallRecordingService extends Service{
    private MediaRecorder mediaRecorder;
    public static final String ACTION = "com.aphidgrp.callrecorder.services.CallRecordingService";
    private boolean rung = false;
    private String phoneNumber = "Unknown";
    private Thread aThread;
    private boolean recording = false;
    private static String mFileName = null;


    @Override
    public void onCreate()
    {
        //Toast.makeText(this, "service is running", Toast.LENGTH_LONG).show();
        //super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String state = intent.getExtras().getString("intentType");
        Log.i("info", "state is "+state);
        if(state.equals("INCOMING"))
        {
            String phoneNumber = intent.getExtras().getString("phoneNumber");
            if(phoneNumber != null){
               this.phoneNumber = phoneNumber;
            }
            Log.i("info", "incoming call");
           this.rung = true;
        }
        if(state.equals("OUTGOING"))
        {
            String phoneNumber = intent.getExtras().getString("phoneNumber");
            if(phoneNumber != null){
                this.phoneNumber = phoneNumber;
            }
            Log.i("info", "outgoing call");
        }
        if(state.equals("ANSWERED"))
        {
            Log.i("info", "ANSWERED");
            if(this.rung){
                Toast.makeText(this, "Incoming Call "+this.phoneNumber, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Outgoing Call "+this.phoneNumber, Toast.LENGTH_LONG).show();
            }
            if(!this.recording){
                this.recording = true;
                Log.i("info", "start recording");
                startRecording();
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
    public void onDestroy() {
    //stopRecording();
        Toast.makeText(this, "service is destroyed", Toast.LENGTH_LONG).show();
        if(this.recording){
            stopRecording();
        }
    }

    public void startRecording(){
        Log.i("info", "im going to start recording");
        mediaRecorder = new MediaRecorder();
        Log.i("info", "i created a mediarecorder");
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        Log.i("info", "i set my source to voice call");
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        Log.i("info", "im set my format to default");
        mediaRecorder.setMaxDuration(0);
        Log.i("info", "im set max duration to 0");
        this.mFileName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
        Log.i("info", "this is my new file path "+this.mFileName);
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        this.mFileName += ts+".wav";
        Log.i("info", "this is my new file path and file name "+this.mFileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        Log.i("info", "set my audio recorder ");
        mediaRecorder.setOutputFile(this.mFileName);
        Log.i("info", "set the output");
        try {
            mediaRecorder.prepare();
            Log.i("info", "prepare");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
        Log.i("info", "start");
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
