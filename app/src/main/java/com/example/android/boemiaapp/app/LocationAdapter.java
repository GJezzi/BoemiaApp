package com.example.android.boemiaapp.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.boemiaapp.R;

import java.util.ArrayList;

/**
 * Created by gjezzi on 24/03/16.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationAdapterViewHolder> {

    private ArrayList<LocationInfo> locationInfo;

    public LocationAdapter (ArrayList<LocationInfo> locationInfo) {
        this.locationInfo = locationInfo;
    }

    public class LocationAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mLocationName;
        public final TextView mLocationAddress;

        public LocationAdapterViewHolder(View view) {
            super(view);
            mLocationName = (TextView) view.findViewById(R.id.list_item_location_name_textview);
            mLocationAddress = (TextView) view.findViewById(R.id.list_item_location_address_textview);
            view.setOnClickListener(this);
        }

        public void onClick(View v) {

        }
    }

    @Override
    public LocationAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_location, viewGroup, false);
        view.setFocusable(true);
        return new LocationAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationAdapterViewHolder locationAdapterViewHolder, int position) {
        TextView locationName = locationAdapterViewHolder.mLocationName;
        TextView locationAddress = locationAdapterViewHolder.mLocationAddress;

        locationAddress.setText(locationInfo.get(position).getLocationAddress());
        locationName.setText(locationInfo.get(position).getLocationName());
    }

    @Override
    public int getItemCount() {
        return locationInfo.size();
    }
}
