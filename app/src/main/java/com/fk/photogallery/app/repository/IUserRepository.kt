package com.fk.photogallery.app.repository

import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.RequestDataCallBack

interface IUserRepository {
    fun getPhotoItem(
        coreBean: CoreBean,
        requestDataCallBack: RequestDataCallBack<CoreBean>
    )
}