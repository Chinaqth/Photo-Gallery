package com.fk.photogallery.app.repository

import com.fk.photogallery.base.model.dao.TabMenu
import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.RequestDataCallBack

interface IUserRepository {
    fun getPhotoItem(
        tabMenu: TabMenu,
        coreBean: CoreBean,
        requestDataCallBack: RequestDataCallBack<CoreBean>
    )
}