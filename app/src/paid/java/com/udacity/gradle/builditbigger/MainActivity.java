package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import cz.jtek.jokeactivitylib.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.pb_loading);
        mButton = findViewById(R.id.btn_tell_joke);
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
    protected void onPause() {
        super.onPause();

        // Hide loading indicator
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }

        // Show button
        if (mButton != null) {
            mButton.setVisibility(View.VISIBLE);
        }

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

        // Start joke fetching
        runEndpointsAsyncTask();
    }

    private void runEndpointsAsyncTask() {

        // Show loading indicator
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        // Hide button
        if (mButton != null) {
            mButton.setVisibility(View.INVISIBLE);
        }

        // Start joke fetching
        EndpointsAsyncTask eat = new EndpointsAsyncTask(new EndpointsAsyncTask.EndpointsAsyncTaskListener<String>() {
            @Override
            public void onSuccess(String result) {
                startJokeDisplayActivity(result);
            }

            @Override
            public void onFailure(Exception e) {
                startJokeDisplayActivity(e.getMessage());
            }
        });

        eat.execute();
    }

    public void startJokeDisplayActivity(String jokeText) {
        Intent displayIntent = new Intent(this, JokeDisplayActivity.class);
        displayIntent.putExtra(JokeDisplayActivity.KEY_JOKE, jokeText);
        startActivity(displayIntent);
    }

}
