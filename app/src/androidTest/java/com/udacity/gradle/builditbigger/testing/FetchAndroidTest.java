package com.udacity.gradle.builditbigger.testing;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Log;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class FetchAndroidTest {

    private static final String TAG = FetchAndroidTest.class.getSimpleName();

    private String mResult;

    /**
     * Verify that EndpointsAsyncTask fetches non-null string from API.
     */
    @Test
    public void testJokeFetching() {

        final EndpointsAsyncTask.EndpointsAsyncTaskListener<String> listener =
                new EndpointsAsyncTask.EndpointsAsyncTaskListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d(TAG, "onSuccess: " + result);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                };

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(listener);
        endpointsAsyncTask.execute();

        try {
            mResult = endpointsAsyncTask.get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Assert that result is not empty
        assertFalse(TextUtils.isEmpty(mResult));
    }

}
