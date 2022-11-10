package com.fk.photogallery.app.repository.impl

import android.text.TextUtils
import android.util.Log
import com.fk.photogallery.app.repository.IUserRepository
import com.fk.photogallery.base.constutil.BaseConst
import com.fk.photogallery.base.model.dao.TabMenu
import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.HttpUtil
import com.fk.photogallery.core.utils.net.RequestDataCallBack

class UserRepositoryImpl : IUserRepository {
    override fun getPhotoItem(
        tabMenu: TabMenu,
        coreBean: CoreBean,
        requestDataCallBack: RequestDataCallBack<CoreBean>
    ) {
        var page = 1
        if (coreBean.hits != null) {
            page = coreBean.page + 1
        }
        if (!TextUtils.isEmpty(tabMenu.url)) {
            val requestUrl = "${tabMenu.url}&lang=zh&page=$page"
            HttpUtil.get(CoreBean::class.java, requestUrl, requestDataCallBack)
        }
    }
}