package com.example.android.boemiaapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.boemiaapp.R;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

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
