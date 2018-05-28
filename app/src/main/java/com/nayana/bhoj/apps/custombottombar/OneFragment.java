package com.nayana.bhoj.apps.custombottombar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class OneFragment extends Fragment {

    public static final String TAG = OneFragment.class.getCanonicalName();

    public OneFragment() {
        // Required empty public constructor
        Log.e(TAG, "constructor called");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG, "onCreateView called");
        return inflater.inflate(R.layout.fragment_one, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop called");
    }
}
