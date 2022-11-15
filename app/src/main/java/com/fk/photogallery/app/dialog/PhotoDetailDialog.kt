package com.fk.photogallery.app.dialog

import android.content.Context
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import com.fk.photogallery.R
import com.fk.photogallery.base.dialog.BaseDialogK
import com.fk.photogallery.base.model.dao.PhotoItem
import com.fk.photogallery.base.utils.viewbinding.viewBinding


class PhotoDetailDialog(context: Context, private var photoItem: PhotoItem) :
    BaseDialogK(context,R.style.base_dialog) {
    private lateinit var ivImage: ImageView
    override fun layoutId(): Int = R.layout.dialog_photo_detail

    override fun initContentView() {
        super.initContentView()
        window?.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
        ivImage = findViewById(R.id.iv_image)
        displayWithUrl(R.id.iv_image, photoItem.largeImageURL, -1)
    }

    override fun initViewAction() {
        super.initViewAction()
    }

}