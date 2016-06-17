package com.example.android.boemiaapp.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by gjezzi on 22/03/16.
 */

@RealmClass
public class Locations extends RealmObject {

    @PrimaryKey
    private long mId;

    private String mLocationName;
    private String mLocationAddress;
    private float mRating;

    @Ignore
    private String mType;
    private double mLatitude;
    private double mLongitude;

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

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Locations)) return false;
        Locations secondPlace = (Locations) o;
        return this.mLatitude == secondPlace.getLat() && this.mLongitude == secondPlace.getLong();
    }

}


