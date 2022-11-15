package com.fk.photogallery.core.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log


class PhotoGalleryApplication : Application() {
    private var instance: PhotoGalleryApplication? = null
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
    }


    fun getAppInstance() : PhotoGalleryApplication {
        return this
    }


}