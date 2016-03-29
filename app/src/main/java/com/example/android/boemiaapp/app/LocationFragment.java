package com.example.android.boemiaapp.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.boemiaapp.R;

/**
 * Created by gjezzi on 18/03/16.
 */
public class LocationFragment extends Fragment {

    public static final String LOG_TAG = LocationFragment.class.getSimpleName();

    public LocationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        return rootView;
    }
}
