package com.aphidgrp.callrecorder.database.entity;

import android.content.Context;

import com.orm.SugarRecord;

/**
 * Created by sonny.lloyd on 13/03/2015.
 */
public class Call extends SugarRecord<Call>{
    String number;
    String created;

    public Call(){}

    public Call(String number, String created){
        this.number = number;
        this.created = created;
    }

    public String getNumber() { return this.number; }
    public String getCreated(){
        return this.created;
    }

    public String setNumber(String number) { return this.number = number; }
    public String setCreated(String created){
        return this.created = created;
    }
}
