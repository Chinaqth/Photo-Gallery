package com.fk.photogallery.app.repository

import com.fk.photogallery.base.model.dao.TabMenu
import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.RequestDataCallBack

interface IPhotoRepository {
    fun getPhotoItem(
        tabMenu: TabMenu,
        coreBean: CoreBean,
        requestDataCallBack: RequestDataCallBack<CoreBean>
    )

    fun getRecommendItem(coreBean: CoreBean, requestDataCallBack: RequestDataCallBack<CoreBean>)


    fun getPhotosBySearch(
        coreBean: CoreBean,
        keywords: String,
        requestDataCallBack: RequestDataCallBack<CoreBean>
    )
}