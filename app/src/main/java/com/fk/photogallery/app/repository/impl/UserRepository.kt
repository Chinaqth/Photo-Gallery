package com.fk.photogallery.app.repository.impl

import com.fk.photogallery.app.repository.IUserRepository
import com.fk.photogallery.base.constutil.BaseConst
import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.HttpUtil
import com.fk.photogallery.core.utils.net.RequestDataCallBack

class UserRepository : IUserRepository {
    override fun getPhotoItem(
        coreBean: CoreBean,
        requestDataCallBack: RequestDataCallBack<CoreBean>
    ) {
        var requestUrl = "${BaseConst.API}?${BaseConst.API_KEY}"
        var page = 1
        if (coreBean.hits != null) {
            page = coreBean.page + 1
        }
        requestUrl = "$requestUrl&page=$page&lang=zh&image_type=photo"
        HttpUtil.get(CoreBean::class.java,requestUrl,requestDataCallBack)
    }
}