package com.example.android.boemiaapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.boemiaapp.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.ui.PlacePicker;

/**
 * Created by gjezzi on 18/03/16.
 */
public class LocationFragment extends Fragment {

    private final static int PLACE_PICKER_REQUEST = 9191;

    private FloatingActionButton mFAB;

    public LocationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        mFAB = (FloatingActionButton) rootView.findViewById(R.id.location_fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    Intent intent = builder.build(getActivity());
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                }
                catch (GooglePlayServicesRepairableException e) {
                    GooglePlayServicesUtil.showErrorDialogFragment(e.getConnectionStatusCode(), getActivity(), 0);

                } catch (GooglePlayServicesNotAvailableException e) {
                    Toast.makeText(getActivity(), "Google Play Services is not Available", Toast.LENGTH_LONG).show();
                }



            }
        });

        return rootView;
    }
}
