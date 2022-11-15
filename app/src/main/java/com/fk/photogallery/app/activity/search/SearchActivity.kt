package com.fk.photogallery.app.activity.search

import android.os.Bundle
import com.fk.photogallery.R
import com.fk.photogallery.app.activity.search.history.HistoryFragment
import com.fk.photogallery.base.activity.BaseActivity

class SearchActivity : BaseActivity() {
    override fun setLayoutId(): Int = R.layout.activity_search

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_container,HistoryFragment())
        transaction.commit()
    }

    override fun onAfterCreated(savedInstanceState: Bundle?) {

    }

    override fun addViewAction() {

    }
}