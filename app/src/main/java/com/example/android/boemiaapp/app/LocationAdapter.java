package com.example.android.boemiaapp.app;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.boemiaapp.R;

/**
 * Created by gjezzi on 24/03/16.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationAdapterViewHolder> {


    private Cursor mCursor;
    private Context mContext;
    private int mPosition;

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
    public LocationAdapter.LocationAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_location, viewGroup, false);
        view.setFocusable(true);
        return new LocationAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationAdapterViewHolder locationAdapterViewHolder, int position) {
        locationAdapterViewHolder.mLocationAddress.setText(mContext.getText(R.string.location_address_text_view));
        locationAdapterViewHolder.mLocationName.setText(mContext.getText(R.string.location_name_text_view));
        mPosition = locationAdapterViewHolder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return mPosition;
    }
}
