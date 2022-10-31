package com.fk.photogallery.core.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fk.photogallery.base.model.RuntimeData;


public abstract class CoreActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onBeforeCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        RuntimeData.getInstance().setCurrentActivity(this);
        onCreateContent(savedInstanceState);
        onAfterCreated(savedInstanceState);
    }


    protected void onBeforeCreate(Bundle savedInstanceState) {

    }

    protected void onCreateContent(Bundle savedInstanceState) {

    }

    protected void onAfterCreated(Bundle savedInstanceState) {
        addViewAction();
    }

    protected void addViewAction() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}