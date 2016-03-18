package com.example.android.boemiaapp.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.boemiaapp.R;

/**
 * Created by gjezzi on 18/03/16.
 */
public class LocationFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public LocationFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_location_list);
        mRecyclerView.setHasFixedSize(true);

        return rootView;
    }
}
