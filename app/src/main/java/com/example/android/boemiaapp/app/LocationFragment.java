package com.example.android.boemiaapp.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.boemiaapp.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;

/**
 * Created by gjezzi on 18/03/16.
 */
public class LocationFragment extends Fragment {

    private final String LOG_TAG = LocationFragment.class.getSimpleName();
    private final static int PLACE_PICKER_REQUEST = 1;


    private FloatingActionButton mFAB;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;


    public LocationFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.location_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<LocationInfo> locationInfo = new ArrayList<>();
        for (int i = 0; i < LocationData.locationAddress.length; i++){
            locationInfo.add(new LocationInfo(LocationData.locationName[i],
                    LocationData.locationAddress[i]));
        }

        mAdapter = new LocationAdapter(locationInfo);
        mRecyclerView.setAdapter(mAdapter);

        mFAB = (FloatingActionButton) rootView.findViewById(R.id.location_fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    Intent intent = builder.build(getActivity());
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                    Log.d(LOG_TAG, "Place Picker Opened");
                }
                catch (GooglePlayServicesRepairableException e) {
                    GooglePlayServicesUtil.showErrorDialogFragment(e.getConnectionStatusCode(), getActivity(), 0);
                    Log.d(LOG_TAG, "GooglePlayServicesRepairableException thrown");
                    e.printStackTrace();

                } catch (GooglePlayServicesNotAvailableException e) {
                    Toast.makeText(getActivity(), "Google Play Services is not Available", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(getActivity(), data);

                CharSequence name = place.getName();
                CharSequence address = place.getAddress();
                String placeID = place.getId();
                String attribution = PlacePicker.getAttributions(data);

                if (attribution == null) {
                    attribution = "";
                }

            }

        }
    }
}
