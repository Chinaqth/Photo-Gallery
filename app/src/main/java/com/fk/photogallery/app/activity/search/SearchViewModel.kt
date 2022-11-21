package com.fk.photogallery.app.activity.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fk.photogallery.app.repository.impl.PhotoRepositoryImpl
import com.fk.photogallery.app.utils.HistoryManager
import com.fk.photogallery.base.model.dao.PhotoItem
import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.RequestDataCallBack

class SearchViewModel : ViewModel() {
    private val _photoItem = MutableLiveData<ArrayList<PhotoItem>>()
    val photoItem: LiveData<ArrayList<PhotoItem>>
    get() = _photoItem
    var keyword : String = ""
    private var coreBean = CoreBean()
    private var hit = ArrayList<PhotoItem>()

    fun fetchData(keyword: String) {
        PhotoRepositoryImpl().getPhotosBySearch(coreBean, keyword, requestDataCallBack)
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