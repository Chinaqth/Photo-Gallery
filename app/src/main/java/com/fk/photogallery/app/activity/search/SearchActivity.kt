package com.fk.photogallery.app.activity.search

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.ansen.shape.AnsenTextView
import com.fk.photogallery.R
import com.fk.photogallery.app.activity.search.history.HistoryFragment
import com.fk.photogallery.app.activity.search.result.ResultFragment
import com.fk.photogallery.base.activity.BaseActivity

class SearchActivity : BaseActivity() {
    private lateinit var editText: EditText
    private lateinit var tvSearch: AnsenTextView
    private var historyFragment : HistoryFragment? = null
    private var resultFragment : ResultFragment? = null

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

        setViewOnClickListener(R.id.iv_back, onClickListener)
        setViewOnClickListener(R.id.iv_cancel, onClickListener)
        setViewOnClickListener(R.id.tv_search, onClickListener)
    }

    private val onClickListener = View.OnClickListener { view ->
            if (view.id == R.id.iv_back) {
                finish()
            } else if (view.id == R.id.iv_cancel) {
                editText.setText("")
                historyFragment?.let {
                    if (it.isVisible) {
                        return@OnClickListener
                    } else{
                        switchBack()
                    }
                }
            } else if (view.id == R.id.tv_search) {
                if (tvSearch.isSelected) {
                    finish()
                } else {
                    switchToResultFragment()
                }
            }
        }

    private fun switchToResultFragment() {
        if (historyFragment?.isAdded == true && historyFragment?.isVisible == true) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.hide(historyFragment!!)
            resultFragment = ResultFragment()
            transaction.add(R.id.fl_container,resultFragment!!)
            transaction.commit()
        }
    }

    private fun switchBack() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(resultFragment!!)
        if (historyFragment?.isHidden == true) {
            transaction.show(historyFragment!!)
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        if (historyFragment?.isHidden == true) {
            switchBack()
        } else{
            finish()
        }
    }

}