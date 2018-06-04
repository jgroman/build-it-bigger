package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import cz.jtek.jokeactivitylib.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity {

    private static final String TAG =  MainActivity.class.getSimpleName();

    private Context mContext;

    private ProgressBar mProgressBar;
    private Button mButton;

    private InterstitialAd mInterstitialAd;
    private int mCounterAdLoadRetries;

    private static final int MAX_AD_LOAD_RETRIES = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mProgressBar = findViewById(R.id.pb_loading);
        mButton = findViewById(R.id.btn_tell_joke);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.

                // Retry ad loading
                if (mCounterAdLoadRetries < MAX_AD_LOAD_RETRIES) {
                    mCounterAdLoadRetries++;
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.

                // Running Endpoints task to fetch joke
                runEndpointsAsyncTask();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Hide loading indicator
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }

        // Show button
        if (mButton != null) {
            mButton.setVisibility(View.VISIBLE);
        }

        // Load the next interstitial.
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        // Reset ad load counter
        mCounterAdLoadRetries = 1;
    }

    /**
     * "Tell joke" button click handler
     *
     * @param view View
     */
    public void tellJoke(View view) {

        // Show loading indicator
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        // Hide button
        if (mButton != null) {
            mButton.setVisibility(View.INVISIBLE);
        }

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            // Joke is fetched by onAdClosed() method after the ad is closed
        }
        else {
            // Interstitial ad is not loaded yet or loading failed
            Log.d(TAG, "The interstitial wasn't loaded yet.");

            // Run Endpoints AsyncTask anyway
            runEndpointsAsyncTask();
        }
    }

    private void runEndpointsAsyncTask() {

        // Start joke fetching
        EndpointsAsyncTask eat = new EndpointsAsyncTask(new EndpointsAsyncTask.EndpointsAsyncTaskListener<String>() {
            @Override
            public void onSuccess(String result) {
                startActivity(JokeDisplayActivity.newIntent(mContext, result));
            }

            @Override
            public void onFailure(Exception e) {
                startActivity(JokeDisplayActivity.newIntent(mContext, e.getMessage()));            }
        });
        eat.execute();
    }
}
