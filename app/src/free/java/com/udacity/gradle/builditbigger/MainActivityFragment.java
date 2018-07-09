package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import guskuma.com.jokeactivities.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{

    private Loader<String> mLoader;
    private ProgressBar mProgressBar;
    private Button mTellJokeButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mProgressBar = root.findViewById(R.id.progress_bar);
        mTellJokeButton = root.findViewById(R.id.tellJokeButton);

        mTellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTellJokeButton.setEnabled(false);
                mProgressBar.setVisibility(View.VISIBLE);
                mLoader.forceLoad();
            }
        });

        mLoader = getLoaderManager().initLoader(EndpointsAsyncTaskLoader.TASK_ID, null, this);

        return root;
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new EndpointsAsyncTaskLoader(getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

        mProgressBar.findViewById(R.id.progress_bar).setVisibility(View.INVISIBLE);
        mTellJokeButton.setEnabled(true);

        if(s == null) {
            Toast.makeText(this.getActivity(), R.string.joke_load_error, Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.PARAM_JOKE, s);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        mLoader = null;
    }
}