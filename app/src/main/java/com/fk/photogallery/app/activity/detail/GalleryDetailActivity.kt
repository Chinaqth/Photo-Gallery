package com.fk.photogallery.app.activity.detail

import android.os.Bundle
import android.text.TextUtils
import com.fk.photogallery.R
import com.fk.photogallery.base.activity.BaseActivity
import com.fk.photogallery.base.model.dao.IntentParma
import com.fk.photogallery.base.model.dao.PhotoItem
import java.io.Serializable

class GalleryDetailActivity : BaseActivity() {
    private lateinit var intentParam: IntentParma
    private lateinit var photoItem: PhotoItem
    override fun setLayoutId(): Int = R.layout.activity_gallery_detail

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
    }

    override fun onAfterCreated(savedInstanceState: Bundle?) {
        super.onAfterCreated(savedInstanceState)
        intent?.let {
            if (TextUtils.equals(it.action, "intent_put")) {
                intentParam = it.getSerializableExtra(it.action) as IntentParma
            }
        }

        photoItem = intentParam.photoItem
        
    }
}