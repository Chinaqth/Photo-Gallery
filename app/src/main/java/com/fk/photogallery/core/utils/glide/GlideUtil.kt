package com.fk.photogallery.core.utils.glide

import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fk.photogallery.R
import com.fk.photogallery.base.model.RuntimeData

object GlideUtil {

    private val options = RequestOptions().error(R.mipmap.icon_load_fail)

    fun disPlayWithLocalData(url : String, defaultImage : Int, imageView: ImageView) {
        RuntimeData.getInstance().context.let { context ->
            TextUtils.isEmpty(url).let {
                if (it) {
                    imageView.setImageResource(defaultImage)
                } else{
                    Glide.with(context)
                        .load(url)
                        .apply(options)
                        .placeholder(defaultImage)
                }
            }
        }
    }
}