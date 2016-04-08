package com.example.android.boemiaapp.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.boemiaapp.R;

import java.util.ArrayList;

/**
 * Created by gjezzi on 24/03/16.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private Context mContext;
    private ArrayList<LocationInfo> mLocations;

    public LocationAdapter (Context context, ArrayList<LocationInfo> locations) {
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
            Toast.makeText(mContext, mLocationName.getText().toString(), Toast.LENGTH_SHORT).show();
            int adapterPosition = getAdapterPosition();
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

        LocationInfo locationInfo = mLocations.get(position);

        locationAdapterViewHolder.mLocationName.setText(locationInfo.getLocationName());
        locationAdapterViewHolder.mLocationAddress.setText(locationInfo.getLocationAddress());
    }

    @Override
    public int getItemCount() {
        if (mLocations == null) return 0;
        return mLocations.size();
    }
}
