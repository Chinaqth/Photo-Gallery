package com.fk.photogallery.app.activity.search.result


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fk.photogallery.R
import com.fk.photogallery.app.activity.search.SearchViewModel
import com.fk.photogallery.base.fragment.BaseFragmentK
import com.fk.photogallery.base.utils.viewbinding.viewBinding
import com.fk.photogallery.databinding.FragmnetResultBinding
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener

class ResultFragment : BaseFragmentK(R.layout.fragmnet_result), OnLoadMoreListener {

    private val binding: FragmnetResultBinding by viewBinding()
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var resultAdapter: ResultAdapter

    override fun onCreateContent(view: View, savedInstanceState: Bundle?) {
        super.onCreateContent(view, savedInstanceState)
        resultAdapter = ResultAdapter()
    }

    override fun addViewAction() {
        super.addViewAction()
        binding.refreshLayout.setEnableRefresh(false)
        binding.refreshLayout.setOnLoadMoreListener(this)
        binding.recyclerview.run {
            layoutManager = LinearLayoutManager(context)
            adapter = resultAdapter
        }
        activity?.let { activity ->
            viewModel.photoItem.observe(activity) {
                binding.refreshLayout.apply {
                    if (isLoading) {
                        finishLoadMore(500)
                    }
                }
                resultAdapter.setItems(it)
            }
        }
    }

    override fun onFirstLoad() {
        super.onFirstLoad()
        viewModel.let {
            it.fetchData(it.keyword)
        }
    }

    override fun onFragmentVisibleChange(visible: Boolean) {
        super.onFragmentVisibleChange(visible)
        if (visible) {
            viewModel.let {
                it.fetchData(it.keyword)
            }
        }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        viewModel.let {
            it.fetchData(it.keyword)
        }
    }


}