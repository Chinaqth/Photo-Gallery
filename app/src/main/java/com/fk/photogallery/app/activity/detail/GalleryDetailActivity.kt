package com.fk.photogallery.app.activity.detail

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fk.photogallery.R
import com.fk.photogallery.base.activity.BaseActivity
import com.fk.photogallery.base.model.dao.IntentParma
import com.fk.photogallery.base.model.dao.PhotoItem
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import java.io.Serializable

class GalleryDetailActivity : BaseActivity() , OnRefreshLoadMoreListener{
    private lateinit var intentParam: IntentParma
    private lateinit var photoItem: PhotoItem
    private lateinit var recyclerView: RecyclerView
    private lateinit var detailRecommendAdapter: DetailRecommendAdapter
    private lateinit var detailRecommendViewModel : DetailRecommendViewModel

    override fun setLayoutId(): Int = R.layout.activity_gallery_detail

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
        smartRefreshLayout = findViewById(R.id.refreshLayout)
        recyclerView = findViewById(R.id.recyclerview)
        detailRecommendAdapter = DetailRecommendAdapter()
        detailRecommendViewModel = ViewModelProvider(this)[DetailRecommendViewModel::class.java]
    }

    override fun onAfterCreated(savedInstanceState: Bundle?) {
        super.onAfterCreated(savedInstanceState)
        intent?.let {
            if (TextUtils.equals(it.action, "intent_put")) {
                intentParam = it.getSerializableExtra(it.action) as IntentParma
            }
        }
        photoItem = intentParam.photoItem
        initView()
        initRecyclerView()
        detailRecommendViewModel.getFirst()
    }

    override fun addViewAction() {
        super.addViewAction()
        smartRefreshLayout.setOnRefreshLoadMoreListener(this)
    }

    private fun initView() {
        displayWithUrl(R.id.iv_avatar,photoItem.userImageURL,R.mipmap.icon_default_avatar)
        displayWithUrl(R.id.iv_image,photoItem.largeImageURL,-1)
        setText(R.id.tv_theme,photoItem.tags)
        setText(R.id.tv_nickname,photoItem.user)
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@GalleryDetailActivity)
            adapter = detailRecommendAdapter
        }
        detailRecommendViewModel.photoItem.observe(this) {
            smartRefreshLayout.apply {
                if (isRefreshing) {
                    detailRecommendAdapter.setItems(it)
                    finishRefresh(300)
                } else if (isLoading) {
                    finishLoadMore(500)
                }
            }
            detailRecommendAdapter.addItems(it)
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        detailRecommendViewModel.getFirst()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        detailRecommendViewModel.getNext()
    }

}