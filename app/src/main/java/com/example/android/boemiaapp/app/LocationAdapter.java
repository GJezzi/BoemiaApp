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


import com.example.android.boemiaapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by gjezzi on 24/03/16.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> implements RatingBar.OnRatingBarChangeListener{

    private Context mContext;
    private ArrayList<Locations> mLocations;
    int mCount;
    float mCurRating;

    public LocationAdapter (Context context, ArrayList<Locations> locations) {
        this.mContext = context;
        this.mLocations = locations;

    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        public final TextView mLocationName;
        public final RatingBar mAlertDialogRatingBar;
        public final RatingBar mViewHolderRatingBar;
        //public final TextView mLocationAddress;
        //public final TextView mType;

        public LocationViewHolder(Context context, View view) {
            super(view);

            this.mContext = context;
            mLocationName = (TextView) view.findViewById(R.id.list_item_location_name_textview);
            mAlertDialogRatingBar = (RatingBar) view.findViewById(R.id.alert_dialog_rating_bar);
            mViewHolderRatingBar = (RatingBar) view.findViewById(R.id.list_item_location_rating_bar);

            //mLocationAddress = (TextView) view.findViewById(R.id.list_item_location_address_textview);
            //mType = (TextView) view.findViewById(R.id.list_item_location_type_text_view);

        }

    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_location, viewGroup, false);
        view.setFocusable(true);
        final LocationViewHolder viewHolder = new LocationViewHolder(mContext, view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = viewHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION){
                    beerRatingAlertDialog();
                    //Toast.makeText(mContext, mLocationName.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocationViewHolder locationAdapterViewHolder, int position) {
        Locations locations = mLocations.get(position);

        locationAdapterViewHolder.mLocationName.setText(locations.getLocationName());
        locationAdapterViewHolder.mViewHolderRatingBar.setRating(mCurRating);
        //locationAdapterViewHolder.mLocationAddress.setText(locations.getLocationAddress());
        //locationAdapterViewHolder.mType.setText(locations.getPlaceType());
    }

    @Override
    public int getItemCount() {
        if (mLocations == null) return 0;
        return mLocations.size();
    }

    private void beerRatingAlertDialog() {
        final float rating = 0;
        AlertDialog alertDialog;
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(null);

        View ratingDialogView = inflater.inflate(R.layout.rating_alert_dialog, null, false);
        final RatingBar alertDialogRatingBar = (RatingBar) ratingDialogView.findViewById(R.id.alert_dialog_rating_bar);
        alertDialogRatingBar.setRating(rating);
        alertDialogRatingBar.setOnRatingBarChangeListener(this);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setView(ratingDialogView);
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromTouch) {
        DecimalFormat decimalFormat = new DecimalFormat("#,#");
        mCurRating = Float.valueOf(decimalFormat.format((mCurRating * mCount + rating) / mCount));
        ratingBar.setRating(mCurRating);
        notifyDataSetChanged();
    }
}

