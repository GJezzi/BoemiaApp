package com.example.android.boemiaapp.app;

/**
 * Created by gjezzi on 22/03/16.
 */
public class Locations {

//    private GoogleApiClient mGoogleApiClient;
//    private Locations mLocationInfo;
//    private TextView mTextView;

    private String mLocationName;
    private String mLocationAddress;
    private String mType;

    private double mLatitude;
    private double mLongitude;
    private float mRating;
    private int mCount;

    public String getLocationName() { return mLocationName; }

    public void setLocationName(String locationName) {
        this.mLocationName = locationName;
    }

    public String getLocationAddress() { return mLocationAddress; }

    public void setLocationAddress(String locationAddress) {
        this.mLocationAddress = locationAddress;
    }

    public String getPlaceType() { return mType; }

    public void setLocationType(String type) {
        this.mType = type;
    }

    public double getLat() { return mLatitude; }

    public void setLat(double latitude) {
        this.mLatitude = latitude;
    }

    public double getLong() { return mLongitude; }

    public void setLong(double longitude) {
        this.mLongitude = longitude;
    }

    public float getRating() { return mRating; }

    public void setRating(float rating) { this.mRating = rating; }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Locations)) return false;
        Locations secondPlace = (Locations) o;
        return this.mLatitude == secondPlace.getLat() && this.mLongitude == secondPlace.getLong();
    }

}


