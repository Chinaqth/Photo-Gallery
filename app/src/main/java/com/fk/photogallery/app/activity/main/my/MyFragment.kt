package com.fk.photogallery.app.activity.main.my

import android.util.Log
import com.fk.photogallery.R
import com.fk.photogallery.base.fragment.BaseFragment

class MyFragment : BaseFragment() {

    override fun setLayoutId(): Int = R.layout.fragment_my


    override fun onFirstLoad() {
        super.onFirstLoad()
        Log.d("QQQ", this.javaClass.name + ", onFirstLoad :")
    }


}