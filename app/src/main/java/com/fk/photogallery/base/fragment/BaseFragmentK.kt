package com.fk.photogallery.base.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.scwang.smart.refresh.layout.SmartRefreshLayout

open class BaseFragmentK(@LayoutRes val layoutRes: Int) :Fragment() {
    var smartRefreshLayout: SmartRefreshLayout? = null
    private var isCreated = false
    private var isFirst = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCreated = true

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateContent(view, savedInstanceState)
        onFragmentIsReady(userVisibleHint)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        onFragmentIsReady(!hidden)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        onFragmentIsReady(userVisibleHint)
    }

    protected open fun onCreateContent(view: View, savedInstanceState: Bundle?) {

    }

    /***
     * 当fragment可见状态改变时调用
     * @param visible 当前fragmnet 是否可见
     */
    protected open fun onFragmentIsReady(visible: Boolean) {
        if (!isCreated) {
            return
        }
        //第一次加载
        if (isFirst && visible) {
            Log.d("BaseFragment", this.javaClass.name + ", onFirstLoad :")
            onFirstLoad()
            isFirst = false
        } else if (!isFirst) {
            Log.d("BaseFragment", this.javaClass.name + ", visible :" + visible)
            onFragmentVisibleChange(visible)
        }
    }

    protected open fun onFirstLoad() {
        addViewAction()
    }

    protected open fun onFragmentVisibleChange(visible: Boolean) {}

    fun autoRefresh() {
        smartRefreshLayout?.apply {
            autoRefresh(500)
        }
    }

    protected open fun addViewAction() {

    }

    override fun onDestroy() {
        super.onDestroy()
        isCreated = false
    }

}