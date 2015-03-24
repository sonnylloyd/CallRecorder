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

/**
 * Created by Sonny on 19/03/2015.
 */
public class callAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources resource;
    Call call = null;

    public static class ViewHolder{
        public TextView number;
        public TextView created;
    }

    public callAdapter(Activity activity, ArrayList data, Resources resource){
        this.activity = activity;
        this.data = data;
        this.resource = resource;
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
        View view = convertView;
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            viewHolder.number = (TextView) view.findViewById(R.id.row_number);
            viewHolder.created = (TextView) view.findViewById(R.id.row_created);
            view.setTag( viewHolder );
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

        if(data.size()<=0)
        {
            //ViewHolder.text.setText("No Data");
        }else{
            call = null;
            call = ( Call ) data.get( position );
            viewHolder.number.setText(call.getNumber());
            viewHolder.created.setText(call.getCreated());
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        Log.d("AphidGrp", "List Item Clicked");
    }
}
