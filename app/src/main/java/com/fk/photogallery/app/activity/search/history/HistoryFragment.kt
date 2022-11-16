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

    private val binding : FragmnetHistoryBinding by viewBinding()
    private lateinit var historyAdapter : HistoryAdapter
    private val searchViewModel : SearchViewModel by activityViewModels()

    override fun onCreateContent(view: View, savedInstanceState: Bundle?) {
        super.onCreateContent(view, savedInstanceState)
        historyAdapter = HistoryAdapter()
    }

    override fun onFirstLoad() {
        super.onFirstLoad()
    }

    override fun onResume() {
        super.onResume()
        historyAdapter.addItems(HistoryManager.getHistories())
        historyAdapter.notifyDataSetChanged()
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
            historyAdapter.setItems(HistoryManager.getHistories())
            historyAdapter.notifyDataSetChanged()
        }
    }

}