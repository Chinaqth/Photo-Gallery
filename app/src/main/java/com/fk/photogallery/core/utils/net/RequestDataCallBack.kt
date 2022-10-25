package com.fk.photogallery.core.utils.net

interface RequestDataCallBack<T> {

    fun dataCallBack(status : Int, obj : T) {

    }

}