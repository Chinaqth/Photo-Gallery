package com.fk.photogallery.base.model.dao

import com.fk.photogallery.core.model.dao.CoreBean

data class GalleryItem(
    var items: List<PhotoItem>?
) : CoreBean()