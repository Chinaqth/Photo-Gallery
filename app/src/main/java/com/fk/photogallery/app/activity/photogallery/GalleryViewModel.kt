package com.fk.photogallery.app.activity.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fk.photogallery.base.model.dao.GalleryItem
import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.HttpUtil
import com.fk.photogallery.core.utils.net.RequestDataCallBack

class GalleryViewModel : ViewModel() {
    private val _photoItem = MutableLiveData<CoreBean>()
    val photoItem : LiveData<CoreBean>
    get() = _photoItem

    fun fetchData() {
        HttpUtil.get(
            CoreBean::class.java,"https://pixabay.com/api/?key=30818692-f884b930db1e529bdb3eadd40&image_type=photo",
            requestDataCallBack)
    }

    private val requestDataCallBack = object : RequestDataCallBack<CoreBean> {
        override fun dataCallBack(status: Int, obj: CoreBean) {
            super.dataCallBack(status, obj)
            Log.d("QQQ", "callback$obj")
            _photoItem.postValue(obj)
        }
    }
}