package com.fk.photogallery.base.model.dao

data class PhotoItem(
    var id: Int,
    var previewURL: String,
    var likes: Int,
    var comments: Int
)