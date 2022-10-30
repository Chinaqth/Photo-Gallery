package com.fk.photogallery.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fk.photogallery.core.fragment.CoreFkFragment;
import com.fk.photogallery.core.model.dao.CoreBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;


public abstract class BaseFkFragment extends CoreFkFragment {
    protected SmartRefreshLayout smartRefreshLayout;
    protected View rootView;


    @Override
    protected void onCreateContent(Bundle savedInstanceState) {
        super.onCreateContent(savedInstanceState);
        rootView = LayoutInflater.from(getContext()).inflate(setLayoutId(), null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return rootView;
    }

    protected abstract int setLayoutId();


    //用的时候需要判空，可能空指针
    protected <T extends View> T findViewById(@IdRes int resId) {
        if (rootView != null) {
            return rootView.findViewById(resId);
        }
        return null;
    }

    public void autoRefresh() {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.autoRefresh(500);
        }
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
                smartRefreshLayout.finishRefresh(100);
            } else {
                if (obj.isLastPage()) {
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    smartRefreshLayout.finishLoadMore(500);
                }
            }
        }
    }

}
