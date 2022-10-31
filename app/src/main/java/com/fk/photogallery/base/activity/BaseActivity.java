package com.fk.photogallery.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.IdRes;

import com.fk.photogallery.core.activity.CoreActivity;
import com.fk.photogallery.core.model.dao.CoreBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

public abstract class BaseActivity extends CoreActivity {
    protected SmartRefreshLayout smartRefreshLayout;
    protected void onCreateContent(Bundle savedInstanceState) {
        setContentView(setLayoutId());
    }


    public void setViewOnClickListener(@IdRes int resId, View.OnClickListener onClickListener) {
        View view = findViewById(resId);
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }


    public void requestDataFinish(CoreBean obj) {
        if (smartRefreshLayout != null) {
            if (obj.isPullRefresh()) {
                smartRefreshLayout.finishRefresh(5);
            } else {
                smartRefreshLayout.finishLoadMore(5);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View v = getCurrentFocus();
            //如果不是落在EditText区域，则需要关闭输入法
            if (hideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    protected boolean hideKeyboard(View view, MotionEvent event) {
        if ((view instanceof EditText)) {
            int[] location = {0, 0};
            view.getLocationInWindow(location);

            //获取现在拥有焦点的控件view的位置，即EditText
            int left = location[0], top = location[1], bottom = top + view.getHeight(), right = left + view.getWidth();
            //判断我们手指点击的区域是否落在EditText上面，如果不是，则返回true，否则返回false
            boolean isInEt = (event.getX() > left && event.getX() < right && event.getY() > top
                    && event.getY() < bottom);
            return !isInEt;
        }
        return false;
    }

    protected abstract int setLayoutId();
}
