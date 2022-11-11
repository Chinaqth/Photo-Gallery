package com.fk.photogallery.base.adapter.recyclerview

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fk.photogallery.core.utils.glide.GlideUtil.disPlayWithUrl

@Keep
open class ZViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * Views indexed with their IDs
     */
    private val views: SparseArray<View> = SparseArray()

    open fun <T : View> getView(@IdRes viewId: Int): T {
        val view = getViewOrNull<T>(viewId)
        checkNotNull(view) { "No view found with id $viewId" }
        return view
    }

    @Suppress("UNCHECKED_CAST")
    open fun <T : View> getViewOrNull(@IdRes viewId: Int): T? {
        val view = views.get(viewId)
        if (view == null) {
            itemView.findViewById<T>(viewId)?.let {
                views.put(viewId, it)
                return it
            }
        }
        return view as? T
    }

    open fun setText(@IdRes viewId: Int, value: CharSequence?): ZViewHolder {
        getViewOrNull<TextView>(viewId)?.text = value
        return this
    }

    open fun setText(@IdRes viewId: Int, @StringRes strId: Int): ZViewHolder? {
        getViewOrNull<TextView>(viewId)?.setText(strId)
        return this
    }

    open fun setTextColor(@IdRes viewId: Int, @ColorInt color: Int): ZViewHolder {
        getViewOrNull<TextView>(viewId)?.setTextColor(color)
        return this
    }

    open fun setTextColorRes(@IdRes viewId: Int, @ColorRes colorRes: Int): ZViewHolder {
        getViewOrNull<TextView>(viewId)?.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                colorRes
            )
        )
        return this
    }

    open fun setImageResource(@IdRes viewId: Int, @DrawableRes imageResId: Int): ZViewHolder {
        getViewOrNull<ImageView>(viewId)?.setImageResource(imageResId)
        return this
    }

    open fun setImageDrawable(@IdRes viewId: Int, drawable: Drawable?): ZViewHolder {
        getViewOrNull<ImageView>(viewId)?.setImageDrawable(drawable)
        return this
    }

    open fun setImageBitmap(@IdRes viewId: Int, bitmap: Bitmap?): ZViewHolder {
        getViewOrNull<ImageView>(viewId)?.setImageBitmap(bitmap)
        return this
    }

    open fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int): ZViewHolder {
        getViewOrNull<View>(viewId)?.setBackgroundColor(color)
        return this
    }

    open fun setBackgroundResource(
        @IdRes viewId: Int,
        @DrawableRes backgroundRes: Int
    ): ZViewHolder {
        getViewOrNull<View>(viewId)?.setBackgroundResource(backgroundRes)
        return this
    }

    open fun setVisible(@IdRes viewId: Int, isVisible: Int): ZViewHolder {
        getViewOrNull<View>(viewId)?.visibility = isVisible
        return this
    }

    open fun setShowHide(@IdRes viewId: Int, isShow: Boolean): ZViewHolder {
        setVisible(viewId, if (isShow) View.VISIBLE else View.GONE)
        return this
    }

    open fun show(@IdRes viewId: Int): ZViewHolder {
        setShowHide(viewId, true)
        return this
    }

    open fun hide(@IdRes viewId: Int): ZViewHolder {
        setShowHide(viewId, false)
        return this
    }

    open fun setEnabled(@IdRes viewId: Int, isEnabled: Boolean): ZViewHolder {
        getViewOrNull<View>(viewId)?.isEnabled = isEnabled
        return this
    }

    open fun setSelect(@IdRes viewId: Int, isSelect: Boolean): ZViewHolder {
        getViewOrNull<View>(viewId)?.isSelected = isSelect
        return this
    }

    open fun displayWithUrl(@IdRes viewId: Int, url: String, defaultImage: Int) {
        val view = getView<ImageView>(viewId)
        disPlayWithUrl(view, url, defaultImage, true)
    }
}