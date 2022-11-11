package com.fk.photogallery.base.utils

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.*

/**
 * @Author zyc
 * @CreateDate 2022/8/15
 * @Description 直接操作View
 */
object ViewUtils {
    /**
     * 提供给Java使用
     * kotlin 考虑使用ViewExt
     */
    @JvmStatic
    fun hide(vararg views: View?) {
        views.forEach {
            it?.visibility = GONE
        }
    }

    @JvmStatic
    fun show(vararg views: View?) {
        views.forEach {
            it?.visibility = VISIBLE
        }
    }

    @JvmStatic
    fun inHide(vararg views: View?) {
        views.forEach {
            it?.visibility = INVISIBLE
        }
    }

    @JvmStatic
    fun showHide(isShow: Boolean, vararg views: View?) {
        if (isShow) {
            show(*views)
        } else {
            hide(*views)
        }
    }

    /**
     * 如方法名
     */
    @JvmStatic
    fun setMarginTop(view: View?, value: Int) {
        view?.setMarginTop(value)
    }

    /**
     * 如方法名
     */
    @JvmStatic
    fun setMarginEnd(view: View?, value: Int) {
        view?.setMarginEnd(value)
    }

    /**
     * 如方法名
     */
    @JvmStatic
    fun setMarginBottom(view: View?, value: Int) {
        view?.setMarginBottom(value)

    }

    /**
     * 如方法名
     */
    @JvmStatic
    fun setMarginStart(view: View?, value: Int) {
        view?.setMarginStart(value)

    }

    /**
     * 如方法名
     */
    @JvmStatic
    fun setMarginHorizontal(view: View?, value: Int) {
        view?.setMarginHorizontal(value)

    }

    /**
     * 如方法名
     */
    @JvmStatic
    fun setMarginVertical(view: View?, value: Int) {
        view?.setMarginVertical(value)
    }

    /**
     * 如方法名
     */
    @JvmStatic
    fun setMargin(view: View?, start: Int, top: Int, end: Int, bottom: Int) {
        view?.setMargin(start, top, end, bottom)
    }

    /**
     * 设置宽和高
     */
    @JvmStatic
    fun setWidthHeight(view: View?, w: Int, h: Int) {
        view?.setWidthHeight(w, h)
    }

    /**
     * see [setWidthHeight]
     * 避免和系统方法重叠
     */
    @JvmStatic
    fun setWidths(view: View?, w: Int) {
        view?.setWidths(w)
    }

    /**
     * see [setWidthHeight]
     * 避免和系统方法重叠
     */
    @JvmStatic
    fun setHeights(view: View?, h: Int) {
        view?.setHeights(h)

    }

    /**
     * 将View的宽高模式设置为自适应
     */
    @JvmStatic
    fun setWarp(view: View?) {
        view?.setWarp()
    }


    @JvmStatic
    fun setPaddingTop(view: View?, value: Int) {
        view?.setPaddingTop(value)
    }

    @JvmStatic
    fun setPaddingStart(view: View?, value: Int) {
        view?.setPaddingStart(value)

    }

    @JvmStatic
    fun setPaddingEnd(view: View?, value: Int) {
        view?.setPaddingEnd(value)
    }

    @JvmStatic
    fun setPaddingBottom(view: View?, value: Int) {
        view?.setPaddingBottom(value)
    }

    @JvmStatic
    fun setPaddingHorizontal(view: View?, value: Int) {
        view?.setPaddingHorizontal(value)
    }

    @JvmStatic
    fun setPaddingHorizontal(view: View?, left: Int, right: Int) {
        view?.setPadding(left, view.paddingTop, right, view.paddingBottom)
    }

    @JvmStatic
    fun setPaddingVertical(view: View?, top: Int, bottom: Int) {
        view?.setPadding(view.left, top, view.right, bottom)
    }


    @JvmStatic
    fun setPaddingVertical(view: View?, value: Int) {
        view?.setPaddingVertical(value)
    }

}

/**
 * About LayoutInflater
 */
fun ViewGroup.getItemView(@LayoutRes layoutResId: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutResId, this, false)
}

/**
 * About margin set
 * see [updateMargins]
 */
fun View.setMarginTop(value: Int) {
    setMargin(this.marginStart, value, this.marginEnd, this.marginBottom)
}

fun View.setMarginEnd(value: Int) {
    setMargin(this.marginStart, this.marginTop, value, this.marginBottom)
}

fun View.setMarginBottom(value: Int) {
    setMargin(this.marginStart, this.marginTop, this.marginEnd, value)
}

fun View.setMarginStart(value: Int) {
    setMargin(value, this.marginTop, this.marginEnd, this.marginBottom)
}

fun View.setMargin(start: Int, top: Int, end: Int, bottom: Int) {
    when (this.layoutParams) {
        is ViewGroup.MarginLayoutParams -> {
            updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(start, top, end, bottom)
            }
        }
    }
}

fun View.setMarginHorizontal(value: Int) {
    this.setMargin(value, this.marginTop, value, this.marginBottom)
}

fun View.setMarginVertical(value: Int) {
    this.setMargin(this.marginStart, value, this.marginEnd, value)
}

/**
 * About width height set
 * see [setWidthHeight]
 */
/**
 * @param w 宽度 int
 * @param h 高度 int
 * [setWidths],[setHeights]
 */
fun View.setWidthHeight(w: Int, h: Int) {
    updateLayoutParams {
        height = w
        width = h
    }
}

/**
 * see [setWidthHeight]
 * 避免和系统方法重叠
 */
fun View.setWidths(w: Int) {
    updateLayoutParams {
        width = w
    }
}

/**
 * see [setWidthHeight]
 * 避免和系统方法重叠
 */
fun View.setHeights(h: Int) {
    updateLayoutParams {
        height = h
    }
}

/**
 * 将View的宽高模式设置为自适应
 */
fun View.setWarp() {
    updateLayoutParams {
        height = WRAP_CONTENT
        width = WRAP_CONTENT
    }
}

/**
 * About padding set
 * see [updatePadding]
 */
fun View.setPaddingTop(value: Int) {
    this.setPadding(this.paddingStart, value, this.paddingEnd, this.paddingBottom)
}

fun View.setPaddingStart(value: Int) {
    this.setPadding(value, this.paddingTop, this.paddingEnd, this.paddingBottom)
}

fun View.setPaddingEnd(value: Int) {
    this.setPadding(this.paddingStart, this.paddingTop, value, this.paddingBottom)
}

fun View.setPaddingBottom(value: Int) {
    this.setPadding(this.paddingStart, this.paddingTop, this.paddingEnd, value)
}

fun View.setPaddingHorizontal(value: Int) {
    this.setPadding(value, this.paddingTop, value, this.paddingBottom)
}

fun View.setPaddingVertical(value: Int) {
    this.setPadding(this.paddingStart, value, this.paddingEnd, value)
}


/**
 * [constrainLayoutParams]
 * [relativeLayoutParams]
 * [linearLayoutParams]
 * 直接获取强转后的LayoutParams，注意
 * throw CastException
 */
val View.constrainLayoutParams: ConstraintLayout.LayoutParams get() = this.layoutParams as ConstraintLayout.LayoutParams
val View.relativeLayoutParams: RelativeLayout.LayoutParams get() = this.layoutParams as RelativeLayout.LayoutParams
val View.linearLayoutParams: LinearLayout.LayoutParams get() = this.layoutParams as LinearLayout.LayoutParams