package com.fk.photogallery.app.activity.search.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fk.photogallery.R
import com.fk.photogallery.app.activity.search.SearchViewModel
import com.fk.photogallery.app.utils.FlowLayoutManager
import com.fk.photogallery.app.utils.HistoryManager
import com.fk.photogallery.base.fragment.BaseFragmentK
import com.fk.photogallery.base.utils.viewbinding.viewBinding
import com.fk.photogallery.databinding.FragmnetHistoryBinding

class HistoryFragment : BaseFragmentK(R.layout.fragmnet_history) {

    private val binding: FragmnetHistoryBinding by viewBinding()
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateContent(view: View, savedInstanceState: Bundle?) {
        super.onCreateContent(view, savedInstanceState)
        historyAdapter = HistoryAdapter()
    }

    override fun addViewAction() {
        super.addViewAction()
        binding.tvClearHistory.setOnClickListener() {
            if (it.id == R.id.tv_clear_history) {
                historyAdapter.setItems(HistoryManager.clearHistories())
                binding.tvClearHistory.visibility = View.GONE
                binding.tvHistory.visibility = View.GONE
            }
        }
    }

    override fun onFirstLoad() {
        super.onFirstLoad()
        if (HistoryManager.getHistories().isNotEmpty()) {
            binding.tvClearHistory.visibility = View.VISIBLE
            binding.tvHistory.visibility = View.VISIBLE
        } else {
            binding.tvClearHistory.visibility = View.GONE
            binding.tvHistory.visibility = View.GONE
        }
        historyAdapter.setItems(HistoryManager.getHistories())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHistory.apply {
            layoutManager = FlowLayoutManager()
            adapter = historyAdapter
        }
    }


    override fun onFragmentVisibleChange(visible: Boolean) {
        super.onFragmentVisibleChange(visible)
        if (visible) {
            if (HistoryManager.getHistories().isNotEmpty()) {
                binding.tvClearHistory.visibility = View.VISIBLE
                binding.tvHistory.visibility = View.VISIBLE
            }
            historyAdapter.setItems(HistoryManager.getHistories())
        }
    }

}