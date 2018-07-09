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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import guskuma.com.jokeactivities.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{

    private Loader<String> mLoader;
    private ProgressBar mProgressBar;
    private Button mTellJokeButton;
    private InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = root.findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());

        mInterstitialAd = new InterstitialAd(this.getActivity());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());

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
    public void onLoadFinished(@NonNull Loader<String> loader, final String s) {

        mProgressBar.findViewById(R.id.progress_bar).setVisibility(View.INVISIBLE);
        mTellJokeButton.setEnabled(true);

        if(s == null) {
            Toast.makeText(this.getActivity(), R.string.joke_load_error, Toast.LENGTH_LONG).show();
            return;
        }

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.setAdListener(new AdListener(){
                                              @Override
                                              public void onAdClosed() {
                                                  startJokeActivity(s);
                                              }
                                          }
            );
            mInterstitialAd.show();
        } else {
            startJokeActivity(s);
        }
    }

    private void startJokeActivity(String s) {
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
