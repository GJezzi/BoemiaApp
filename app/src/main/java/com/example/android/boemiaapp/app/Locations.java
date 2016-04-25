package com.example.android.boemiaapp.app;

import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by gjezzi on 22/03/16.
 */
public class Locations {

    private GoogleApiClient mGoogleApiClient;
    private Locations mLocationInfo;
    private TextView mTextView;

    private String mLocationName;
    private String mLocationAddress;
    private String mOpen;

    private double mLatitude;
    private double mLongitude;

    public String getLocationName() { return mLocationName; }

    public void setLocationName(String locationName) {
        this.mLocationName = locationName;
    }

    public String getLocationAddress() { return mLocationAddress; }

    public void setLocationAddress(String locationAddress) {
        this.mLocationAddress = locationAddress;
    }

    public String getLocationOpened() { return mOpen; }

    public void setLocationOpened(String open) {
        this.mOpen = open;
    }

    public double getLat() { return mLatitude; }

    public void setLat(double latitude) {
        this.mLatitude = latitude;
    }

    public double getLong() { return mLongitude; }

    public void setLong(double longitude) {
        this.mLongitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Locations)) return false;
        Locations secondPlace = (Locations) o;
        return this.mLatitude == secondPlace.getLat() && this.mLongitude == secondPlace.getLong();
    }

}


