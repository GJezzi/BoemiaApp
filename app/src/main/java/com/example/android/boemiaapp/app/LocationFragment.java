package com.example.android.boemiaapp.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjezzi on 18/03/16.
 */
public class LocationFragment extends android.support.v4.app.Fragment  {

    private final String LOG_TAG = LocationFragment.class.getSimpleName();
    private final static int PLACE_PICKER_REQUEST = 1;

    private ArrayList<Locations> mLocations = new ArrayList<>();
    private LocationAdapter mLocationAdapter;

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

        RecyclerView recyclerView;
        FloatingActionButton floatingActionButton;

        recyclerView = (RecyclerView) rootView.findViewById(R.id.location_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        View emptyView = rootView.findViewById(R.id.recycler_view_location_empty);

        recyclerView.setHasFixedSize(true);

        mLocationAdapter = new LocationAdapter(getActivity(), mLocations);
        recyclerView.setAdapter(mLocationAdapter);

        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.location_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
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
                Place selectedPlace = PlacePicker.getPlace(getActivity(), data);

                final CharSequence mName = selectedPlace.getName();
                final float mRating = selectedPlace.getRating();
                //final CharSequence mAddress = selectedPlace.getAddress();
                final List mType = selectedPlace.getPlaceTypes();
                LatLng latLong = selectedPlace.getLatLng();

                String attributions = PlacePicker.getAttributions(data);
                if (attributions == null) {
                    attributions = "";
                }

                Locations locationInfo = new Locations();
                locationInfo.setLocationName(mName.toString());
                locationInfo.setRating(mRating);
                //locationInfo.setLocationAddress(mAddress.toString());
                locationInfo.setLat(latLong.latitude);
                locationInfo.setLong(latLong.longitude);
                //locationInfo.setLocationType(mType.toString());

                if (mLocations.contains(locationInfo)) {
                    mLocations.remove(locationInfo);
                }
                    mLocations.add(locationInfo);
                    mLocationAdapter.notifyDataSetChanged();

            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }

        }

    }
}
