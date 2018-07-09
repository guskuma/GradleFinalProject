package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class BackendTest extends SupportLoaderTestCase{

    @Override
    @Before
    public void setUp() throws Exception {
        this.mContext = InstrumentationRegistry.getContext();
    }

    @Test
    public void JokeEndpointTest(){
        String result = getLoaderResultSynchronously(new EndpointsAsyncTaskLoader(getContext()));
        assertNotNull(result);

    }


}
