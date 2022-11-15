package com.fk.photogallery.app.activity.detail.recommend

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fk.photogallery.R
import com.fk.photogallery.base.fragment.BaseFragmentK
import com.fk.photogallery.base.model.dao.TabMenu
import com.fk.photogallery.base.utils.viewbinding.viewBinding
import com.fk.photogallery.databinding.FragmentDetailRecommendBinding
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

class DetailRecommendFragment(var tabMenu: TabMenu) : BaseFragmentK(R.layout.fragment_detail_recommend) , OnRefreshLoadMoreListener{
    private val binding : FragmentDetailRecommendBinding by viewBinding()
    private val detailRecommendViewModel : DetailRecommendViewModel by viewModels()
    private lateinit var detailRecommendAdapter: DetailRecommendAdapter

    override fun onCreateContent(view: View, savedInstanceState: Bundle?) {
        detailRecommendAdapter = DetailRecommendAdapter()
        detailRecommendViewModel.initTab(tabMenu)

    }
    override fun addViewAction() {
        binding.refreshLayout.setOnRefreshLoadMoreListener(this)
        binding.recyclerview.run {
            layoutManager = LinearLayoutManager(context)
            adapter = detailRecommendAdapter
        }
        detailRecommendViewModel.photoItem.observe(this) {
            binding.refreshLayout.apply {
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

    override fun onFirstLoad() {
        super.onFirstLoad()
        detailRecommendViewModel.getFirst()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        detailRecommendViewModel.getFirst()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        detailRecommendViewModel.getNext()
    }
}