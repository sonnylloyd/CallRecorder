package com.aphidgrp.callrecorder.cabs;


import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.aphidgrp.callrecorder.R;
import com.aphidgrp.callrecorder.adapters.callAdapter;

/**
 * Created by sonny.lloyd on 02/06/2015.
 */
public class CallLogCab implements ActionMode.Callback{


    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.list_view_actions, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        callAdapter.mActionMode = null;
        mode.finish();
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        callAdapter.mActionMode = null;
    }
}
