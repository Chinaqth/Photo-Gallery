package com.fk.photogallery.base.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fk.photogallery.core.fragment.CoreFragment;
import com.fk.photogallery.core.model.dao.CoreBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;


public abstract class BaseFragment extends CoreFragment {
    protected SmartRefreshLayout smartRefreshLayout;
    protected View rootView;
    private boolean isCreated = false;
    private boolean isFirst = true;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = true;
        onFragmentIsReady(getUserVisibleHint());
    }

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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        onFragmentIsReady(!hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onFragmentIsReady(getUserVisibleHint());
    }

    /***
     * 当fragment可见状态改变时调用
     * @param visible 当前fragmnet 是否可见
     */
    protected void onFragmentIsReady(boolean visible) {
        if (!isCreated) {
            return;
        }
        //第一次加载
        if (isFirst && visible) {
            Log.d("BaseFragment",this.getClass().getName() + ", onFirstLoad :");
            onFirstLoad();
            isFirst = false;
        } else if (!isFirst){
            Log.d("BaseFragment",this.getClass().getName() + ", visible :" + visible);
            onFragmentVisibleChange(visible);
        }
    }

    protected void onFirstLoad() {

    }

    protected void onFragmentVisibleChange(boolean visible) {

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        isCreated = false;
    }
}
