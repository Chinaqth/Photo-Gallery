/**
 * Copyright 2013 Joan Zapata
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fk.photogallery.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.fk.photogallery.core.utils.glide.GlideUtil;

import java.util.HashSet;
import java.util.LinkedHashSet;


/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    /**
     * Views indexed with their IDs
     */
    private final SparseArray<View> views;

    //public Set<Integer> getNestViews() {
//        return nestViews;
//    }

    //private final HashSet<Integer> nestViews;

    private final LinkedHashSet<Integer> childClickViewIds;

    private final LinkedHashSet<Integer> itemChildLongClickViewIds;
    private BaseQuickAdapter adapter;
    private GlideUtil glideUtil;

    /**
     * Package private field to retain the associated user object and detect a change
     */
    private Object associatedObject;
    public int mPosition;

    public BaseViewHolder(final View view) {
        super(view);
        this.views = new SparseArray<>();
        this.childClickViewIds = new LinkedHashSet<>();
        this.itemChildLongClickViewIds = new LinkedHashSet<>();
        glideUtil = GlideUtil.INSTANCE;
    }

    public HashSet<Integer> getItemChildLongClickViewIds() {
        return itemChildLongClickViewIds;
    }

    public HashSet<Integer> getChildClickViewIds() {
        return childClickViewIds;
    }

    /**
     * Will set the text of a TextView.
     *
     * @param viewId The view id.
     * @param value  The text to put in the text view.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setText(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        if (view != null && value != null) {
            view.setText(value);
        }
        return this;
    }

    public void displayWithUrl( @IdRes int viewId, String url, int defaultImage) {
        ImageView view = getView(viewId);
        if (view != null) {
            if (glideUtil != null) {
                glideUtil.disPlayWithUrl(view, url, defaultImage,true);
            }
        }
    }

    public BaseViewHolder setAnsenViewAge(@IdRes int viewId, String age, boolean isMan) {
        TextView view = getView(viewId);
        if (view != null && age != null) {
            view.setText(age);
            view.setSelected(isMan);
            view.setVisibility(TextUtils.isEmpty(age) ? View.GONE : View.VISIBLE);
        }
        return this;
    }

    /**
     * 文本未空时主动隐藏
     *
     * @param viewId
     * @param value
     * @return
     */
    public BaseViewHolder setTextVisible(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        if (view != null) {
            if (TextUtils.isEmpty(value)) {
                view.setVisibility(View.GONE);
            } else {
                view.setText(value);
                view.setVisibility(View.VISIBLE);
            }
        }
        return this;
    }

    public BaseViewHolder setTextInVisible(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        if (view != null) {
            if (TextUtils.isEmpty(value)) {
                view.setVisibility(View.INVISIBLE);
            } else {
                view.setText(value);
                view.setVisibility(View.VISIBLE);
            }
        }
        return this;
    }


    /**
     * 文本未空或等于判定符号时，主动隐藏
     *
     * @param viewId
     * @param value
     * @param decisionSymbol 判定符号
     * @return
     */
    public BaseViewHolder setTextVisible(@IdRes int viewId, CharSequence value, CharSequence decisionSymbol) {
        TextView view = getView(viewId);
        if (view != null) {
            if (TextUtils.isEmpty(value) || TextUtils.equals(value, decisionSymbol)) {
                view.setVisibility(View.GONE);
            } else {
                view.setText(value);
                view.setVisibility(View.VISIBLE);
            }
        }
        return this;
    }

    public BaseViewHolder setText(@IdRes int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(strId);
        }
        return this;
    }

    public BaseViewHolder setSelect(@IdRes int viewId, boolean select) {
        View view = getView(viewId);
        if (view != null) {
            view.setSelected(select);
        }
        return this;
    }

    /**
     * Will set the image of an ImageView from a resource id.
     *
     * @param viewId     The view id.
     * @param imageResId The image resource id.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        ImageView view = getView(viewId);
        if (view != null) {
            view.setImageResource(imageResId);
        }
        return this;
    }


    /**
     * Will set background color of a view.
     *
     * @param viewId The view id.
     * @param color  A color, not a resource id.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        if (view != null) {
            view.setBackgroundColor(color);
        }
        return this;
    }

    /**
     * Will set background of a view.
     *
     * @param viewId        The view id.
     * @param backgroundRes A resource to use as a background.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set text color of a TextView.
     *
     * @param viewId    The view id.
     * @param textColor The text color (not a resource id).
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setTextColor(textColor);
        }
        return this;
    }

    public BaseViewHolder setTextSize(@IdRes int viewId, int unit, float size) {
        TextView view = getView(viewId);
        if (view != null) {
            view.setTextSize(unit, size);
        }
        return this;
    }

    /**
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId   The view id.
     * @param drawable The image drawable.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Add an action to set the image of an image view. Can be called multiple times.
     */
    public BaseViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    public BaseViewHolder setAlpha(@IdRes int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * Set a view visibility to VISIBLE (true) or INVISIBLE (false).
     *
     * @param viewId  The view id.
     * @param visible True for VISIBLE, false for INVISIBLE.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setVisible(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        if (view != null) {
            view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
        return this;
    }

    public BaseViewHolder setVisible(@IdRes int viewId, int visible) {
        View view = getView(viewId);
        if (view != null) {
            view.setVisibility(visible);
        }
        return this;
    }

    /**
     * Sets the on click listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The on click listener;
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    public BaseViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener, boolean accessibility) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    public BaseViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener, Object obj) {
        View view = getView(viewId);
        if (view != null) {
            view.setTag(viewId, obj);
            view.setOnClickListener(listener);
        }
        return this;
    }


    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param tag    The tag;
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setTag(@IdRes int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param key    The key of tag;
     * @param tag    The tag;
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setTag(@IdRes int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * Sets the checked status of a checkable.
     *
     * @param viewId  The view id.
     * @param checked The checked status;
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setChecked(@IdRes int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(checked);
        }
        return this;
    }

    /**
     * Set the enabled state of this view.
     *
     * @param viewId The view id.
     * @param enable The checked status;
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setEnabled(@IdRes int viewId, boolean enable) {
        View view = getView(viewId);
        view.setEnabled(enable);
        return this;
    }

    /**
     * Sets the adapter of a adapter view.
     *
     * @param viewId  The view id.
     * @param adapter The adapter;
     * @return The BaseViewHolder for chaining.
     */
    @SuppressWarnings("unchecked")
    public BaseViewHolder setAdapter(@IdRes int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * Sets the adapter of a adapter view.
     *
     * @param adapter The adapter;
     * @return The BaseViewHolder for chaining.
     */
    protected BaseViewHolder setAdapter(BaseQuickAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public TextView getTextView(@IdRes int viewId) {
        TextView view = getView(viewId);
        return view;
    }

    public ImageView getImageView(@IdRes int viewId) {
        ImageView view = getView(viewId);
        return view;
    }


    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            if (view != null) {
                views.put(viewId, view);
            }
        }
        return (T) view;
    }

    @Nullable
    public <T extends ViewGroup.LayoutParams> T getLayoutParam(@IdRes int viewId) {
        View view = getView(viewId);
        if (view == null) {
            return null;
        } else {
            return (T) view.getLayoutParams();
        }
    }

    /**
     * Retrieves the last converted object on this view.
     */
    public Object getAssociatedObject() {
        return associatedObject;
    }

    /**
     * Should be called during convert
     */
    public void setAssociatedObject(Object associatedObject) {
        this.associatedObject = associatedObject;
    }

}
