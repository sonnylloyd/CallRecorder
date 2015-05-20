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

import com.aphidgrp.callrecorder.database.entity.Call;

import java.io.File;
import java.io.IOException;

public class CallRecordingService extends Service{
    private MediaRecorder mediaRecorder;
    private Call call;
    private String phoneNumber = "Unknown";
    private String Type;
    private boolean recording = false;
    private String mFileDir = Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + "CallRecorder";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String state = (intent.hasExtra("intentType")?intent.getExtras().getString("intentType"):null);
        this.phoneNumber = (intent.hasExtra("phoneNumber")?intent.getExtras().getString("phoneNumber"):this.phoneNumber);
        if(state.equals("INCOMING") || state.equals("OUTGOING"))
        {
            this.Type = state;
        }
        if(state.equals("ANSWERED"))
        {
            if(!this.recording){
                call = new Call();
                call.save();
                this.recording = true;
                //startRecording();
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service is destroyed", Toast.LENGTH_LONG).show();
        if(this.recording){
            //stopRecording();
        }
    }

    public void startRecording(){

        if(!isExternalStorageWritable()){
            this.mFileDir = this.getFilesDir().getAbsolutePath();
        }
        File output = new File(this.mFileDir);
        if(!output.exists() && !output.isDirectory()) {
            output.mkdir();
        }
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString()+".wav";
        String outputFile = output.getAbsolutePath()+'/'+ts;
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(outputFile);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();
        Toast.makeText(this, "recording", Toast.LENGTH_LONG).show();
    }

    public void stopRecording(){
        mediaRecorder.stop();
        mediaRecorder.reset();
        mediaRecorder.release();
        this.recording = false;
        Toast.makeText(this, "stop recording", Toast.LENGTH_LONG).show();
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
