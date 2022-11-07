package com.fk.photogallery.core.utils.glide

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.fk.photogallery.R
import com.fk.photogallery.base.model.RuntimeData

object GlideUtil {

    private val options = RequestOptions()
        .error(R.mipmap.icon_load_fail)
        .format(DecodeFormat.PREFER_RGB_565)

    fun disPlayWithLocalData(imageView: ImageView, localImage: Int) {
        imageView.setImageResource(localImage)
    }

    fun disPlayWithUrl(imageView: ImageView, url: String, defaultImage: Int, useCache: Boolean) {
        var strategy = DiskCacheStrategy.DATA
        if (!useCache) {
            strategy = DiskCacheStrategy.NONE //不使用磁盘缓存
        }
        RuntimeData.getInstance().context.let { context ->
            TextUtils.isEmpty(url).let {
                if (it) {
                    imageView.setImageResource(defaultImage)
                } else {
                    Glide.with(context)
                        .load(url)
                        .skipMemoryCache(!useCache)
                        .apply(options)
                        .placeholder(defaultImage)
                        .diskCacheStrategy(strategy)
                        .into(imageView)
                }
            }
        }

    }
}