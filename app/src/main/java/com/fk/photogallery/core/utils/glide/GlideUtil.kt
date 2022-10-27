package com.fk.photogallery.core.utils.glide

import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fk.photogallery.R
import com.fk.photogallery.core.application.PhotoGalleryApplication
import com.fk.photogallery.core.model.RunTimeData

object GlideUtil {



    fun disPlayWithLocalData(url : String, defaultImage : Int, imageView: ImageView) {
        RunTimeData.getContext().let {

            TextUtils.isEmpty(url).let {
                if (it) {
                    imageView.setImageResource(defaultImage)
                } else{
                    Glide.with(RunTimeData.getContext())
                        .load(url)
                        .placeholder(defaultImage)
                        .errorPlaceholder()

                }
            }


        }
    }
}