package com.fk.photogallery.app.activity.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fk.photogallery.app.repository.impl.UserRepository
import com.fk.photogallery.base.model.dao.GalleryItem
import com.fk.photogallery.base.model.dao.PhotoItem
import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.HttpUtil
import com.fk.photogallery.core.utils.net.RequestDataCallBack

class GalleryViewModel : ViewModel() {
    private val _photoItem = MutableLiveData<ArrayList<PhotoItem>>()
    val photoItem : LiveData<ArrayList<PhotoItem>>
    get() = _photoItem
    private var coreBean = CoreBean()
    private var hit = ArrayList<PhotoItem>()

    fun getFirst() {
        hit.clear()
        UserRepository().getPhotoItem(coreBean,requestDataCallBack)
    }

    fun getNext() {
        UserRepository().getPhotoItem(coreBean,requestDataCallBack)

    }

    private val requestDataCallBack = object : RequestDataCallBack<CoreBean> {
        override fun dataCallBack(status: Int, obj: CoreBean) {
            super.dataCallBack(status, obj)
            coreBean.page += 1
            coreBean.hits = obj.hits
            obj.apply {
                hits?.let {
                    hit.addAll(hits)
                    _photoItem.postValue(hit)
                }
            }
        }
    }

//    fun fetchData() {
//        HttpUtil.get(
//            CoreBean::class.java,"https://pixabay.com/api/?key=30818692-f884b930db1e529bdb3eadd40&page=1&image_type=photo",
//            requestDataCallBack)
//    }
//
}