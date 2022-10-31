package com.fk.photogallery.base.model;


import android.content.Context;

import com.fk.photogallery.core.activity.CoreActivity;
import com.fk.photogallery.core.application.PhotoGalleryApplication;

import java.lang.ref.SoftReference;

public class RuntimeData {

    private SoftReference<CoreActivity> currentActivity = null;

    private static class InstanceHolder {
        private static RuntimeData instance = new RuntimeData();
    }

    public static RuntimeData getInstance() {
        return InstanceHolder.instance;
    }

    public static void setInstance(RuntimeData runtimeData) {
        InstanceHolder.instance = runtimeData;
    }

    public RuntimeData() {
    }

    public CoreActivity getCurrentActivity() {
        if (currentActivity != null) {
            CoreActivity activity = currentActivity.get();
            return activity;
        }
        return null;
    }

    public void setCurrentActivity(CoreActivity currentActivity) {
        if (currentActivity != null) {
            this.currentActivity = new SoftReference<>(currentActivity);
        }
    }

    public Context getContext() {
        return PhotoGalleryApplication.context;
    }
}
