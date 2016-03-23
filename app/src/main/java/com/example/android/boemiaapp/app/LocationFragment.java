package com.example.android.boemiaapp.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.boemiaapp.R;

/**
 * Created by gjezzi on 18/03/16.
 */
public class LocationFragment extends Fragment {

    public static final String LOG_TAG = LocationFragment.class.getSimpleName();

    private TextView mLocationAddressView;
    private TextView mLocationNameView;

    public LocationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        mLocationNameView = (TextView) rootView.findViewById(R.id.list_item_location_name_textview);
        mLocationAddressView = (TextView) rootView.findViewById(R.id.list_item_location_address_textview);

        return rootView;
    }
}
