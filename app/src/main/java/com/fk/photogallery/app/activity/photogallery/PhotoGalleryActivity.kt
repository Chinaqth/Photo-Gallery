package com.fk.photogallery.app.activity.photogallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fk.photogallery.R
import com.fk.photogallery.base.activity.BaseActivity
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.bumptech.glide.Glide
import com.fk.photogallery.base.model.RuntimeData


class PhotoGalleryActivity : BaseActivity(), OnRefreshLoadMoreListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var photoGalleryAdapter: PhotoGalleryAdapter
    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
        galleryViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        smartRefreshLayout = findViewById(R.id.refreshLayout)
        recyclerView = findViewById(R.id.recyclerview)
    }

    override fun addViewAction() {
        super.addViewAction()
        smartRefreshLayout.setOnRefreshLoadMoreListener(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onAfterCreated(savedInstanceState: Bundle?) {
        super.onAfterCreated(savedInstanceState)
        photoGalleryAdapter = PhotoGalleryAdapter()
        galleryViewModel.getFirst()
        galleryViewModel.photoItem.observe(this, {
            smartRefreshLayout.apply {
                if (isRefreshing)  {
                    finishRefresh(300)
                } else if (isLoading) {
                    finishLoadMore(500)
                }
            }
            photoGalleryAdapter.run {
                updateData(it)
                notifyDataSetChanged()
            }
        })

        recyclerView.run {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = null
            adapter = photoGalleryAdapter
            addOnScrollListener(mOnScrollListener)
        }

    }

    private var mOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            when (newState) {
//                SCROLL_STATE_IDLE -> Glide.with(RuntimeData.getInstance().context).resumeRequests()
//                SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING -> Glide.with(RuntimeData.getInstance().context)
//                    .pauseRequests()
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

        }
    }


    override fun onRefresh(refreshLayout: RefreshLayout) {
        galleryViewModel.getFirst()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        galleryViewModel.getNext()
    }
}
