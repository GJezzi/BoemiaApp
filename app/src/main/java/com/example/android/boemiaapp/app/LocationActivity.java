package com.example.android.boemiaapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.boemiaapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {

    private static String LOG_TAG = LocationActivity.class.getSimpleName();
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        checkPlayServices();

        mRecyclerView = (RecyclerView) findViewById(R.id.location_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<LocationInfo> locationInfo = new ArrayList<>();
        for (int i = 0; i < LocationData.locationAddress.length; i++){
            locationInfo.add(new LocationInfo(LocationData.locationName[i],
                    LocationData.locationAddress[i]));
        }

        mAdapter = new LocationAdapter(locationInfo);
        mRecyclerView.setAdapter(mAdapter);

        if (savedInstanceState == null) {
            LocationFragment locationFragment = new LocationFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.location_container, locationFragment)
                    .commit();
        }

    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(LOG_TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
