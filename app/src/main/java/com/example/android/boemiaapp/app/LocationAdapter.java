package com.example.android.boemiaapp.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.boemiaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjezzi on 22/03/16.
 *
 *
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    public List <LocationInfo> locationList;

    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        protected TextView mLocationName;
        protected TextView mLocationAddress;

        public LocationViewHolder(View view) {
            super(view);
            mLocationName = (TextView) view.findViewById(R.id.location_name);
            mLocationAddress = (TextView) view.findViewById(R.id.location_address);
        }
    }

    public LocationAdapter() {
        this.locationList = new ArrayList<>();
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.location_card, viewGroup, false);

        return new LocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        LocationInfo info = locationList.get(position);
        holder.mLocationName.setText(info.locationName);
        holder.mLocationAddress.setText(info.locationAddress);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }
}
