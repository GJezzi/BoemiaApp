package com.example.android.boemiaapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.boemiaapp.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainFragment extends Fragment {

    private LoginButton mFbLoginButton;
    private CallbackManager mCallbackManager;
    private TextView mUserName;
    private FacebookCallback<LoginResult> mFbCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d("Gian", "onSucces");
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            if (profile != null) {
                mUserName.setText("Bem Vindo " + profile.getName());
            }
        }

        @Override
        public void onCancel() {
            Log.d("Gian", "onCancel");
        }

        @Override
        public void onError(FacebookException error) {
            Log.d("Gian", "onError " + error);
        }
    };

    public MainFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCallbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFbLoginButton = (LoginButton) view.findViewById(R.id.fb_login_button);
        mFbLoginButton.setReadPermissions("user_friends");
        mFbLoginButton.setFragment(this);
        mFbLoginButton.registerCallback(mCallbackManager, mFbCallback);

        mUserName = (TextView) view.findViewById(R.id.user_name);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
