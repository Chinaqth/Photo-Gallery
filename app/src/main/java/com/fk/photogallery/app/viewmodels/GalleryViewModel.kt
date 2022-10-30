package com.fk.photogallery.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fk.photogallery.base.constutil.BaseConst
import com.fk.photogallery.base.model.dao.GalleryItem
import com.fk.photogallery.core.utils.net.HttpUtil
import com.fk.photogallery.core.utils.net.RequestDataCallBack

class GalleryViewModel : ViewModel() {
    private val _photoItem = MutableLiveData<GalleryItem>()
    val photoItem : LiveData<GalleryItem>
    get() = _photoItem

    fun fetchData() {
        HttpUtil.get(GalleryItem::class.java,"https://pixabay.com/api/?key=30818692-f884b930db1e529bdb3eadd40&q=yellow+flowers&image_type=photo",
            requestDataCallBack)
    }

    private val requestDataCallBack = object : RequestDataCallBack<GalleryItem> {
        override fun dataCallBack(status: Int, obj: GalleryItem) {
            super.dataCallBack(status, obj)
            _photoItem.value = obj
        }
    }
}