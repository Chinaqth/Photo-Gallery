package com.fk.photogallery.app.activity.photogallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fk.photogallery.R
import com.fk.photogallery.base.activity.BaseActivity
import com.fk.photogallery.base.model.dao.GalleryItem
import com.fk.photogallery.core.model.dao.CoreBean
import com.fk.photogallery.core.utils.net.HttpUtil
import com.fk.photogallery.core.utils.net.RequestDataCallBack
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

class PhotoGalleryActivity : BaseActivity(), OnRefreshLoadMoreListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var galleryViewModel: GalleryViewModel

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
        galleryViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        smartRefreshLayout = findViewById(R.id.refreshLayout)
        recyclerView = findViewById(R.id.recyclerview)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onAfterCreated(savedInstanceState: Bundle?) {
        super.onAfterCreated(savedInstanceState)
        galleryViewModel.fetchData()
        galleryViewModel.photoItem.observe(this, {
                recyclerView.adapter?.run {
                    notifyDataSetChanged()
                }
            })

        recyclerView.run {
            adapter = PhotoGalleryAdapter(this@PhotoGalleryActivity)
            layoutManager = GridLayoutManager(this@PhotoGalleryActivity, 2)
            itemAnimator = null
        }

    }

    override fun addViewAction() {
        super.addViewAction()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {

    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
    }
}
