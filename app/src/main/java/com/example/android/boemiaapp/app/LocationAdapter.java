package com.example.android.boemiaapp.app;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.boemiaapp.R;

import java.util.ArrayList;

/**
 * Created by gjezzi on 24/03/16.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private Context mContext;
    private ArrayList<Locations> mLocations;
    private AlertDialog mAlert;

    public LocationAdapter (Context context, ArrayList<Locations> locations) {
        this.mContext = context;
        this.mLocations = locations;
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        public final TextView mLocationName;
        public final TextView mLocationAddress;

        public LocationViewHolder(Context context, View view) {
            super(view);

            this.mContext = context;
            mLocationName = (TextView) view.findViewById(R.id.list_item_location_name_textview);
            mLocationAddress = (TextView) view.findViewById(R.id.list_item_location_address_textview);
            view.setOnClickListener(this);
        }

        public void onClick(View v) {
            beerRating();
            Toast.makeText(mContext, mLocationName.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_location, viewGroup, false);
        view.setFocusable(true);
        LocationViewHolder viewHolder = new LocationViewHolder(mContext, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocationViewHolder locationAdapterViewHolder, int position) {

        Locations locations = mLocations.get(position);

        locationAdapterViewHolder.mLocationName.setText(locations.getLocationName());
        locationAdapterViewHolder.mLocationAddress.setText(locations.getLocationAddress());
    }

    @Override
    public int getItemCount() {
        if (mLocations == null) return 0;
        return mLocations.size();
    }


    private void beerRating() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(null);
        View ratingDialogView = inflater.inflate(R.layout.rating_alert_dialog, null, false);
        RatingBar ratingBar = (RatingBar) ratingDialogView.findViewById(R.id.beer_rating_bar);
        ratingBar.setRating(0);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setView(ratingDialogView);
        mAlert = builder.create();
        mAlert.show();
    }
}
