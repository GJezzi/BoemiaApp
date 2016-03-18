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
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainFragment extends Fragment {

    private final String LOG_TAG = MainFragment.class.getSimpleName();

    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;
    private CallbackManager mCallbackManager;
    private TextView mUserName;
    private LoginResult mLoginResult;
    private AccessToken mAccessToken;
    private LocationFragment mLocationFragment;

    private FacebookCallback<LoginResult> mFbCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d(LOG_TAG, "Facebook LoginButton onSuccess");
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            mUserName.setText(setupWelcomeMessage(profile));
        }

        @Override
        public void onCancel() {
            Log.d(LOG_TAG, "Facebook LoginButton onCancel");
            if (mLoginResult.getAccessToken() != null) {
                Log.d(LOG_TAG, "Access Token: " + mLoginResult.getAccessToken());
            }
        }

        @Override
        public void onError(FacebookException error) {
            Log.d(LOG_TAG, "Facebook LoginButton onError" + error.getMessage());
            Log.d(LOG_TAG, "Facebook LoginButton onError" + error.getStackTrace());
        }
    };

    public MainFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCallbackManager = CallbackManager.Factory.create();

        checkLoginStatus(AccessToken.getCurrentAccessToken());

        setupTokenTracker();
        setupProfileTracker();

        mTokenTracker.startTracking();
        mProfileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

       return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupUserDetails(view);
        setupFbLoginButton(view);
    }

    @Override
    public void onResume(){
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        mUserName.setText(setupWelcomeMessage(profile));
    }

    @Override
    public void onStop() {
        super.onStop();
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setupUserDetails(View view){
        mUserName = (TextView) view.findViewById(R.id.user_name);
    }

    private void setupTokenTracker() {
        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.d("Current Token: ", "" + currentAccessToken);

            }
        };
    }

    private void setupProfileTracker() {
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.d("Profile: ", "" + currentProfile);
                mUserName.setText(setupWelcomeMessage(currentProfile));
            }
        };
    }

    private void setupFbLoginButton(View view) {
        LoginButton mFbLoginButton;
        mFbLoginButton = (LoginButton) view.findViewById(R.id.fb_login_button);
        mFbLoginButton.setFragment(this);
        mFbLoginButton.setReadPermissions("user_friends");
        mFbLoginButton.registerCallback(mCallbackManager, mFbCallback);
    }

    private String setupWelcomeMessage(Profile profile) {
        StringBuilder stringBuilder = new StringBuilder();
        if (profile != null) {
            stringBuilder.append("Welcome " + profile.getName() + "!");
        }
        return stringBuilder.toString();
    }

    private void checkLoginStatus(AccessToken currentAccessToken) {
        if (currentAccessToken != null) {
            LocationFragment locationFragment = new LocationFragment();
            locationFragment.getContext();



        }
    }
}
