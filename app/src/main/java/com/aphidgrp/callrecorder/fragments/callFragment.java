package com.aphidgrp.callrecorder.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aphidgrp.callrecorder.CallRecorder;
import com.aphidgrp.callrecorder.R;

import com.aphidgrp.callrecorder.adapters.callAdapter;
import com.aphidgrp.callrecorder.database.entity.Call;

/**
 * A placeholder fragment containing a simple view.
 */
public class callFragment extends android.support.v4.app.Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static callFragment newInstance(int sectionNumber) {
        callFragment fragment = new callFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public callFragment() {
        Log.d("aphidgrp", "placement fragment function");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_call_recorder, container, false);
        callAdapter adapter = new callAdapter(this.getActivity(), Call.listAll(Call.class));
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setItemsCanFocus(false);
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CallRecorder) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}