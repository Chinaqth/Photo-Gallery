package com.fk.photogallery.app.activity.search

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import com.ansen.shape.AnsenTextView
import com.fk.photogallery.R
import com.fk.photogallery.app.activity.search.history.HistoryFragment
import com.fk.photogallery.app.activity.search.result.ResultFragment
import com.fk.photogallery.app.utils.HistoryManager
import com.fk.photogallery.base.activity.BaseActivity

class SearchActivity : BaseActivity() {
    private lateinit var editText: EditText
    private lateinit var tvSearch: AnsenTextView
    private var historyFragment: HistoryFragment? = null
    private var resultFragment: ResultFragment? = null

    private val searchViewModel: SearchViewModel by viewModels()

    override fun setLayoutId(): Int = R.layout.activity_search

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
        editText = findViewById(R.id.et_search)
        tvSearch = findViewById(R.id.tv_search)
        val switchTransaction = supportFragmentManager.beginTransaction()
        historyFragment = HistoryFragment()
        switchTransaction.add(R.id.fl_container, historyFragment!!)
        switchTransaction.commit()
    }

    override fun onAfterCreated(savedInstanceState: Bundle?) {
        super.onAfterCreated(savedInstanceState)
    }

    override fun addViewAction() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                if (!TextUtils.isEmpty(editText.text)) {
                    setVisibility(R.id.iv_cancel, View.VISIBLE)
                } else {
                    setVisibility(R.id.iv_cancel, View.GONE)
                }
            }
        })
        editText.setOnEditorActionListener(editorActionListener)
        setViewOnClickListener(R.id.iv_back, onClickListener)
        setViewOnClickListener(R.id.iv_cancel, onClickListener)
        setViewOnClickListener(R.id.tv_search, onClickListener)
        setViewOnClickListener(R.id.tv_clear_history, onClickListener)
    }

    private val editorActionListener = OnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
            search()
        }
        false
    }

    private val onClickListener = View.OnClickListener { view ->
        if (view.id == R.id.iv_back) {
            finish()
        } else if (view.id == R.id.iv_cancel) {
            editText.setText("")
            historyFragment?.let {
                if (it.isVisible) {
                    return@OnClickListener
                } else {
                    switchBack()
                }
            }
        } else if (view.id == R.id.tv_search) {
            search()
        }
    }

    private fun search() {
        if (editText.text.isEmpty()) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show()
            return
        }
        editText.text.toString().let {
            searchViewModel.apply {
                keyword = editText.text.toString()
                HistoryManager.addHistory(it)
            }
        }
        switchToResultFragment()
    }

    private fun switchToResultFragment() {
        if (historyFragment?.isAdded == true && historyFragment?.isVisible == true) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.hide(historyFragment!!)
            if (resultFragment == null) {
                resultFragment = ResultFragment()
                transaction.add(R.id.fl_container, resultFragment!!)
            } else {
                transaction.show(resultFragment!!)
            }
            transaction.commitAllowingStateLoss()
        }
    }

    private fun switchBack() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(resultFragment!!)
        if (historyFragment?.isHidden == true) {
            transaction.show(historyFragment!!)
            transaction.commitAllowingStateLoss()
        }
    }

    override fun onBackPressed() {
        if (historyFragment?.isHidden == true) {
            switchBack()
        } else {
            finish()
        }
    }

}