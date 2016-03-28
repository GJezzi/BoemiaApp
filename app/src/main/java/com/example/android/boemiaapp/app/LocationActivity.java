package com.example.android.boemiaapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.android.boemiaapp.R;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {

    private static String LOG_TAG = LocationActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            LocationFragment locationFragment = new LocationFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.location_container, locationFragment)
                    .commit();
        }
    }
}
