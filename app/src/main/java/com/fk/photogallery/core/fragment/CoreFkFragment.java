package com.fk.photogallery.core.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class CoreFkFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("fragment","onCreate " + getFragmentTAG());
        onBeforeCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        onCreateContent(savedInstanceState);
    }

    protected void onBeforeCreate(Bundle savedInstanceState) {

    }

    protected void onCreateContent(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onAfterCreated(savedInstanceState);
    }

    protected void onAfterCreated(Bundle savedInstanceState) {
        addViewAction();
    }

    protected void addViewAction() {

    }



    public String getFragmentTAG() {
        return this.getClass().getName();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("fragment","onAttach " + getFragmentTAG());
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d("fragment","onStop " + getFragmentTAG());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("fragment","onDestroy " + getFragmentTAG());
    }
}
