package com.example.android.boemiaapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.boemiaapp.R;
import com.facebook.FacebookSdk;


public class MainActivity extends AppCompatActivity {

    public static final String FRAGMENT_TAG = "fragment_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}
