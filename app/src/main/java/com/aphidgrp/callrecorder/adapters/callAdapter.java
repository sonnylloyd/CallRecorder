package com.aphidgrp.callrecorder.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aphidgrp.callrecorder.R;
import com.aphidgrp.callrecorder.database.entity.Call;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sonny on 19/03/2015.
 */
public class callAdapter extends BaseAdapter implements View.OnClickListener, View.OnLongClickListener {
    private Activity activity;
    private List<Call> data;
    private static LayoutInflater inflater = null;

    public static class ViewHolder{
        public TextView number;
        public TextView created;
    }

    public callAdapter(Activity activity, List<Call> data){
        this.activity = activity;
        this.data = data;
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
        View view = this.inflater.inflate(R.layout.call_log_row, parent, false);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        viewHolder.number = (TextView) view.findViewById(R.id.row_number);
        viewHolder.created = (TextView) view.findViewById(R.id.row_created);
        view.setTag(position);

        if(data.size()<=0)
        {
            //ViewHolder.text.setText("No Data");
        }else{
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            Call call = null;
            call = data.get( position );
            viewHolder.number.setText(call.getNumber());
            viewHolder.created.setText(call.getCreated());
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        Call call = data.get(position);
        Toast toast = Toast.makeText(this.activity, call.getNumber(), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onLongClick(View v) {
        int position = (int)v.getTag();
        Call call = data.get(position);
        Toast toast = Toast.makeText(this.activity,"long click : "+ call.getNumber(), Toast.LENGTH_SHORT);
        toast.show();
        return true;
    }
}
