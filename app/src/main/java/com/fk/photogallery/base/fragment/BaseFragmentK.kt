package com.fk.photogallery.base.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.scwang.smart.refresh.layout.SmartRefreshLayout

open class BaseFragmentK(@LayoutRes val layoutRes: Int) : Fragment() {
    var smartRefreshLayout: SmartRefreshLayout? = null
    private var isCreated = false
    private var isFirst = true
    private var rootView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCreated = true

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = LayoutInflater.from(context).inflate(layoutRes, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateContent(view, savedInstanceState)
        onAfterCreated()
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

    protected open fun onAfterCreated() {
        addViewAction()
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
    }

    protected open fun onFragmentVisibleChange(visible: Boolean) {}

    fun autoRefresh() {
        smartRefreshLayout?.apply {
            autoRefresh(500)
        }
    }

    //用的时候需要判空，可能空指针
    protected open fun <T : View?> findViewById(@IdRes resId: Int): T? {
        return rootView?.findViewById(resId)
    }

    protected open fun addViewAction() {

    }

    override fun onDestroy() {
        super.onDestroy()
        isCreated = false
    }

}