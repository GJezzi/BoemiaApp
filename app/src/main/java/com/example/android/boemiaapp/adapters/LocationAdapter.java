package com.example.android.boemiaapp.adapters;

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
import com.example.android.boemiaapp.model.Locations;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by gjezzi on 24/03/16.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    final static String LOG_TAG = LocationAdapter.class.getSimpleName();

    private Context mContext;
    private Realm mRealm;
    private RealmResults<Locations> mLocations;


    public LocationAdapter (Context context, Realm realm, RealmResults<Locations> locations) {
        this.mRealm = realm;
        this.mContext = context;
        this.mLocations = locations;
    }

     public class LocationViewHolder extends RecyclerView.ViewHolder  {
        private Context mContext;
        public final TextView mLocationName;
        public final RatingBar mAlertDialogRatingBar;
        public final RatingBar mViewHolderRatingBar;
        public int mPosition;
        //public final TextView mLocationAddress;
        //public final TextView mType;

        public LocationViewHolder(Context context, View view) {
            super(view);

            this.mContext = context;
            mLocationName = (TextView) view.findViewById(R.id.list_item_location_name_textview);
            mAlertDialogRatingBar = (RatingBar) view.findViewById(R.id.alert_dialog_rating_bar);
            mViewHolderRatingBar = (RatingBar) view.findViewById(R.id.list_item_location_rating_bar);
            mViewHolderRatingBar.setTag(this);
            //mLocationAddress = (TextView) view.findViewById(R.id.list_item_location_address_textview);
            //mType = (TextView) view.findViewById(R.id.list_item_location_type_text_view);
        }
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_location, viewGroup, false);
        LocationViewHolder viewHolder = new LocationViewHolder(mContext, view);
        viewHolder.getAdapterPosition();
        view.setFocusable(true);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final LocationViewHolder locationAdapterViewHolder, final int position) {
        final Locations locations = mLocations.get(position);

        locationAdapterViewHolder.mLocationName.setText(locations.getLocationName());
        locationAdapterViewHolder.mViewHolderRatingBar.setRating(locations.getRating());
        //locationAdapterViewHolder.mLocationAddress.setText(locations.getLocationAddress());
        //locationAdapterViewHolder.mType.setText(locations.getPlaceType());

        locationAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogCreation(position);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return mLocations.get(position).getId();
    }

    @Override
    public int getItemCount() {
        if (mLocations == null) return 0;
        return mLocations.size();
    }

    public void add(long id) {
        Locations locations = new Locations();
        locations.setId(id);

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(locations);
        mRealm.commitTransaction();
        notifyDataSetChanged();
    }

    private void alertDialogCreation(final int position) {
        final float rating = 0;
        AlertDialog alertDialog;
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(null);

        final View ratingDialogView = inflater.inflate(R.layout.rating_alert_dialog, null, false);
        final RatingBar alertDialogRatingBar = (RatingBar) ratingDialogView.findViewById(R.id.alert_dialog_rating_bar);
        alertDialogRatingBar.setRating(rating);
        alertDialogRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                calculateRating(ratingBar, rating, position);
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setView(ratingDialogView);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void calculateRating(RatingBar ratingBar, float rating, int position) {
        Locations location = mLocations.get(position);
        float currRating = location.getRating();
        int count = location.getCount();
        float positionRating = (currRating * count + rating) / ++count;
        location.setRating(positionRating);
        location.setCount(count);
        ratingBar.setRating(rating);
        notifyItemChanged(position);
        Toast.makeText(mContext, "Novo Valor: " + positionRating, Toast.LENGTH_SHORT).show();
    }


}

