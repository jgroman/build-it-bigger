package com.udacity.gradle.builditbigger.testing;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class FetchAndroidTest {

    private static final String TAG = "FetchAndroidTest";

    private Context targetContext;

    @Before
    public void setup() {
        targetContext = InstrumentationRegistry.getTargetContext();
    }

    /**
     * Verify that EndpointsAsyncTask fetches non-null string from API.
     */
    @Test
    public void testJokeFetching() {
        String fetchResult = null;

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute(targetContext);

        try {
            fetchResult = endpointsAsyncTask.get();
            Log.d(TAG, "testJokeFetching: fetched: " + fetchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(fetchResult);
    }

}
