package com.fk.photogallery.app.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fk.photogallery.app.repository.impl.PhotoRepositoryImpl
import com.fk.photogallery.base.model.dao.PhotoItem
import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.RequestDataCallBack

class DetailRecommendViewModel : ViewModel() {
    private val _photoItem = MutableLiveData<ArrayList<PhotoItem>>()
    val photoItem : LiveData<ArrayList<PhotoItem>>
    get() = _photoItem
    private var coreBean = CoreBean()
    private var hit = ArrayList<PhotoItem>()

    fun getFirst() {
        hit.clear()
        coreBean.page = 0
        PhotoRepositoryImpl().getRecommendItem(coreBean,requestDataCallBack)
    }

    fun getNext() {
        PhotoRepositoryImpl().getRecommendItem(coreBean,requestDataCallBack)
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

}