package com.aphidgrp.callrecorder.database.entity;

import android.content.Context;

import com.orm.SugarRecord;

/**
 * Created by sonny.lloyd on 13/03/2015.
 */
public class Call extends SugarRecord<Call>{
    String number;
    String duration;
    String created;

    public Call(){}

    public Call(String number,String duration, String created){
        this.number = number;
        this.duration = duration;
        this.created = created;
    }

    public String getNumber() { return this.number; }
    public String getDuration(){
        return this.duration;
    }
    public String getCreated(){
        return this.created;
    }
}
