package com.udacity.gradle.builditbigger.testing;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Log;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class FetchAndroidTest {

    private static final String TAG = FetchAndroidTest.class.getSimpleName();

    /**
     * Verify that EndpointsAsyncTask fetches non-null string from API.
     */
    @Test
    public void testJokeFetching() {

        final CountDownLatch latch = new CountDownLatch(1);

        final EndpointsAsyncTask.EndpointsAsyncTaskListener<String> listener =
                new EndpointsAsyncTask.EndpointsAsyncTaskListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d(TAG, "onSuccess: " + result);

                        // Assert that result is not empty
                        assertFalse(TextUtils.isEmpty(result));

                        latch.countDown();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());

                        // Assert that result is not empty
                        assertFalse(TextUtils.isEmpty(e.getMessage()));

                        latch.countDown();
                    }
                };

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(listener);
        endpointsAsyncTask.execute();

        try {
            // Wait for synchronized latch to count down to zero
            latch.await();
        }
        catch (InterruptedException iex) {
            Log.d(TAG, "testJokeFetching: Test interrupted.");
        }
    }

}
