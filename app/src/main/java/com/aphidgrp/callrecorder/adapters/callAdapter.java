package com.aphidgrp.callrecorder.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aphidgrp.callrecorder.R;
import com.aphidgrp.callrecorder.database.entity.Call;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sonny on 19/03/2015.
 */
public class callAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private List<Call> data;
    private static LayoutInflater inflater = null;
    public Resources resource;
    Call call = null;

    public static class ViewHolder{
        public TextView number;
        public TextView created;
    }

    public callAdapter(Activity activity, List<Call> data){
        this.activity = activity;
        this.data = data;
        //this.resource = R.layout.call_log_row;
        this.inflater = (LayoutInflater)activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(this.data.size()<=0)
            return 1;
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View view = convertView;
        View view = this.inflater.inflate(R.layout.call_log_row, parent, false);
        ViewHolder viewHolder;
        Log.d("AphidGrp", "ViewHolder viewHolder");
        //if(convertView == null){
            viewHolder = new ViewHolder();
            viewHolder.number = (TextView) view.findViewById(R.id.row_number);
            viewHolder.created = (TextView) view.findViewById(R.id.row_created);
            view.setTag( viewHolder );
            Log.d("AphidGrp", "set viewHolder");
       // }else{
        //    viewHolder = (ViewHolder)view.getTag();
        //}

        if(data.size()<=0)
        {
            //ViewHolder.text.setText("No Data");
            Log.d("AphidGrp", "size 0");
        }else{
            call = null;
            Log.d("AphidGrp", "call null");
            call = data.get( position );
            Log.d("AphidGrp", "call position" + position);
            viewHolder.number.setText(call.getNumber());
            //Log.d("AphidGrp", "view hold number" + call.getNumber());
            viewHolder.created.setText(call.getCreated());
            //Log.d("AphidGrp", "view hold created" + call.getCreated());
        }

        /*for(Iterator<Call> i = data.iterator(); i.hasNext(); ) {
            Call cll = i.next();
            Log.d("AphidGrp", "Number: " + cll.getNumber() + " Created: " + cll.getCreated());
        }*/
        return view;
    }

    @Override
    public void onClick(View v) {
        Log.d("AphidGrp", "List Item Clicked");
    }
}
