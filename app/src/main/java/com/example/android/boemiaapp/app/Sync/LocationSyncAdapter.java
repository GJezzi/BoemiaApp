package com.example.android.boemiaapp.app.sync;

import android.location.Location;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.android.boemiaapp.R;
import com.example.android.boemiaapp.app.LocationAdapter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by gjezzi on 24/03/16.
 */
public class LocationSyncAdapter extends AsyncTask<URL> {
    public final String LOG_TAG = LocationSyncAdapter.class.getSimpleName();

    public static final String ACTION_DATA_UPDATED =
            "com.example.android.boemiaapp.app.ACTION_DATA_UPDATED";

    private final int CLIENT_ID = R.string.foursquare_client_id;
    private final int CLIENT_SECRET = R.string.foursquare_client_secret;

    private final String mLongitude = "40.7463956";
    private final String mLatitude = "-73.9852992";

    private String mTemp;

    List<Location> locationList;
    ArrayAdapter<LocationAdapter> locationAdapter;

    @Override
    protected String doInBackground(View... urls) {
        mTemp = makeCall("https://api.foursquare.com/v2/venues/search?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=20130815&ll=40.7463956,-73.9852992");
        return "";
    }

    protected void onPreExecute() {

    }

    protected void onPostExecute(String result) {

    }

    public static String fourSquareCall(String url) {
        StringBuilder stringBuilder = new StringBuilder(url);

        String replyString = "";

        HttpURLConnection httpURLConnection = null;


    }

}
