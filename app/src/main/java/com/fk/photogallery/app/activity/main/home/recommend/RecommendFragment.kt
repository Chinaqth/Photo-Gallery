package com.fk.photogallery.app.activity.main.home.recommend

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.fk.photogallery.R
import com.fk.photogallery.base.fragment.BaseFragment
import com.fk.photogallery.base.model.RuntimeData
import com.fk.photogallery.base.model.dao.TabMenu
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

class RecommendFragment constructor() : BaseFragment(), OnRefreshLoadMoreListener {

    override fun setLayoutId(): Int = R.layout.fragment_recommend

    private lateinit var recyclerView: RecyclerView
    private lateinit var galleryViewModel: RecommendViewModel
    private lateinit var recommendAdapter: RecommendAdapter
    private var tabMenu : TabMenu? = null


    constructor(tabMenu: TabMenu) : this() {
        this.tabMenu = tabMenu
    }

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
        galleryViewModel = ViewModelProvider(this)[RecommendViewModel::class.java]
        galleryViewModel.initTab(tabMenu)
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
        recommendAdapter = RecommendAdapter()
        galleryViewModel.getFirst()
        galleryViewModel.photoItem.observe(this, {
            smartRefreshLayout.apply {
                if (isRefreshing)  {
                    finishRefresh(300)
                } else if (isLoading) {
                    finishLoadMore(500)
                }
            }
            recommendAdapter.run {
                updateData(it)
                notifyDataSetChanged()
            }
        })

        recyclerView.run {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = null
            adapter = recommendAdapter
        }
    }


    override fun onRefresh(refreshLayout: RefreshLayout) {
        galleryViewModel.getFirst()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        galleryViewModel.getNext()
    }

    override fun autoRefresh() {
        recyclerView.smoothScrollToPosition(0)
        super.autoRefresh()
    }
}