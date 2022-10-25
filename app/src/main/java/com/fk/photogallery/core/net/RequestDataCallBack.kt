package com.fk.photogallery.core.net

interface RequestDataCallBack<T> {

    fun dataCallBack(status : Int, obj : T) {

    }

}