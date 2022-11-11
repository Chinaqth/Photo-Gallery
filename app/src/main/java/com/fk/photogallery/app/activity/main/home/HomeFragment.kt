package com.fk.photogallery.app.activity.main.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.fk.photogallery.R
import com.fk.photogallery.app.activity.main.home.recommend.RecommendFragment
import com.fk.photogallery.base.adapter.BaseViewPagerAdapter
import com.fk.photogallery.base.constutil.BaseConst
import com.fk.photogallery.base.fragment.BaseFragment
import com.fk.photogallery.base.model.dao.TabMenu
import com.flyco.tablayout.SlidingTabLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout

class HomeFragment : BaseFragment() {

    private lateinit var appBarLayout: AppBarLayout
    private lateinit var slidingTabLayout: SlidingTabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var adapter: BaseViewPagerAdapter

    override fun setLayoutId(): Int = R.layout.fragment_home

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
        appBarLayout = findViewById(R.id.app_bar)
        slidingTabLayout = findViewById(R.id.slidingTabLayout)
        viewPager = findViewById(R.id.viewPager)
        collapsingToolbar = findViewById(R.id.collapsing_toolbar)
        adapter = BaseViewPagerAdapter(parentFragmentManager)
        adapter.addFragment(
            RecommendFragment(TabMenu("${BaseConst.API}?${BaseConst.API_KEY}&q=风景")),
            "推荐"
        )
        adapter.addFragment(
            RecommendFragment(TabMenu("${BaseConst.API}?${BaseConst.API_KEY}&q=城市")),
            "热门"
        )
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 6
        slidingTabLayout.setViewPager(viewPager)
    }

    override fun onAfterCreated(savedInstanceState: Bundle?) {
        super.onAfterCreated(savedInstanceState)
    }

    override fun addViewAction() {
        super.addViewAction()
        appBarLayout.run {
            addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
                val percent = Math.abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange
                    .toFloat()
                collapsingToolbar.alpha = 1.0f - percent
            })
        }
        setViewOnClickListener(R.id.rl_search, onClickListener)
        setViewOnClickListener(R.id.iv_avatar, onClickListener)
    }

    private val onClickListener = View.OnClickListener { view ->
        if (view.id == R.id.rl_search) {

        } else if (view.id == R.id.iv_avatar) {

        }
    }

    fun doubleClickRefresh() {
        val fragmentLists: List<BaseFragment> = adapter.fragmentLists
        for ((index, fragment) in fragmentLists.withIndex()) {
            if (index == slidingTabLayout.currentTab) {
                fragment.autoRefresh()
            }
        }
    }
}