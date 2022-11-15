package com.fk.photogallery.app.dialog

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION.SDK
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.drawToBitmap
import com.fk.photogallery.R
import com.fk.photogallery.base.dialog.BaseDialogK
import com.fk.photogallery.base.model.dao.PhotoItem
import com.fk.photogallery.base.utils.SaveUtil
import com.fk.photogallery.base.utils.viewbinding.viewBinding
import java.util.jar.Manifest


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
        findViewById<LinearLayout>(R.id.ll_download).setOnClickListener() {
            SaveUtil.saveBitmap( ivImage.drawToBitmap())
        }
    }

}