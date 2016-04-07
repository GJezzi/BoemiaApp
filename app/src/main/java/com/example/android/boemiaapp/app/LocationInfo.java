package com.example.android.boemiaapp.app;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;

/**
 * Created by gjezzi on 22/03/16.
 */
public class LocationInfo {

    private GoogleApiClient mGoogleApiClient;
    private LocationInfo mLocationInfo;
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

    public void currentPlace() throws SecurityException {
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer likelyPlaces) {
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                //if (placeLikelihood != null && placeLikelihood.getPlace() != null && !TextUtils.isEmpty(placeLikelihood.getPlace().getName())) {

                    placeLikelihood.getPlace().getName();
                    placeLikelihood.getLikelihood();

                    mLocationInfo.setLocationAddress(mLocationAddress);
                    mLocationInfo.setLocationName(mLocationName);

                    mTextView.setText(getLocationName());
                    mTextView.setText(getLocationAddress());
                }
                likelyPlaces.release();

            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LocationInfo)) return false;
        LocationInfo secondPlace = (LocationInfo) o;
        return this.mLatitude == secondPlace.getLat() && this.mLongitude == secondPlace.getLong();
    }

}


