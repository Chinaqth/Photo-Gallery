package com.fk.photogallery.base.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.IdRes
import com.fk.photogallery.R
import com.fk.photogallery.core.utils.glide.GlideUtil
import java.lang.Exception

/**
 * 默认属性
 * Gravity.CENTER
 * ViewGroup.LayoutParams.WRAP_CONTENT
 * ViewGroup.LayoutParams.WRAP_CONTENT
 */
/**
 * 这些参数就相当于一个默认的，有值的构造函数
 */
abstract class BaseDialogK(
    context: Context, themeResId: Int
) : Dialog(context, themeResId) {

    private var isInitAttributes = false
    protected var mGravity: Int = Gravity.CENTER
    protected var mWidth: Int = ViewGroup.LayoutParams.WRAP_CONTENT
    protected var mHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        initViewAction()
        initAttributes()
        if (!isInitAttributes) {
            throw Exception("禁止覆盖 initAttributes() --> super()方法!")
        }

        findViewById<View>(R.id.root)?.let {
            it.setOnClickListener() {
                dismiss()
            }
        }
    }

    /**
     * 初始化view
     * 请调用super，指不定哪天要加点啥
     * TODO 该方法与show()不同步，如果你在[initContentView]初始化View,View声明为lateinit var，[show]时操作'可能'该View会抛出未初始化异常 / 具体请看dialog生命周期
     */
    protected open fun initContentView() {
        setContentView(layoutId())
    }

    /**
     * 初始化事件
     * 请调用super，指不定哪天要加点啥
     */
    protected open fun initViewAction() {
    }

    protected open fun initAttributes(): WindowManager.LayoutParams? {
        isInitAttributes = true
        return window?.attributes?.apply {
            width = mWidth
            height = mHeight
            gravity = mGravity
        }
    }

    /**
     * 只需要传入布局id
     */
    abstract fun layoutId(): Int

    fun displayWithUrl(@IdRes viewId: Int, url: String, defaultImage: Int) {
        findViewById<ImageView>(viewId)?.let {
            GlideUtil.disPlayWithUrl(it, url, defaultImage, false)
        }
    }



}