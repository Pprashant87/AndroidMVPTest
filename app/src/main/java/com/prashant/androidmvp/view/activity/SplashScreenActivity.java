package com.prashant.androidmvp.view.activity;
/**
 * @author : Prashant P
 * @Name: SplashScreenActivity
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.prashant.androidmvp.R;
import com.prashant.androidmvp.utils.AppController;
import com.prashant.androidmvp.utils.network.ConnectivityReceiver;
import com.prashant.androidmvp.view.fragments.MainCountryFragment;


public class SplashScreenActivity extends FragmentActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private static int SPLASH_TIME_OUT = 3000;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initialization();
        displaySplashScreen();
    }

    private void initialization() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        // register connection status listener
        AppController.getInstance().setConnectivityListener(this);
    }

    /*
     * Showing splash screen with a timer. This will be useful when you
     * want to show case your app logo / company
     */
    private void displaySplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainCountryFragment fragment = new MainCountryFragment();
                setFragment(fragment);
            }
        }, SPLASH_TIME_OUT);
    }


    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = getResources().getString(R.string.connected_to_internet);
            color = Color.WHITE;
        } else {
            message = getResources().getString(R.string.not_connected_to_internet);
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
