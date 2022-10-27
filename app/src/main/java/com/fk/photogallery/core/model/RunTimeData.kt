package com.fk.photogallery.core.model

import android.content.Context
import com.fk.photogallery.core.application.PhotoGalleryApplication

object RunTimeData {

    fun getContext() : Context {
        return PhotoGalleryApplication.context
    }
}