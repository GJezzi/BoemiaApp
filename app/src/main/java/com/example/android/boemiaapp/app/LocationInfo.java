package com.example.android.boemiaapp.app;

/**
 * Created by gjezzi on 22/03/16.
 */
public class LocationInfo {

    String locationName;
    String locationAddress;

    public LocationInfo(String locationName, String locationAddress) {
        this.locationAddress = locationAddress;
        this.locationName = locationName;
    }

    public String getLocationName() { return locationName; }

    public String getLocationAddress() { return locationAddress; }

}


