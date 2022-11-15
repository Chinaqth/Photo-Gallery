package com.fk.photogallery.app.activity.detail

import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.fk.photogallery.R
import com.fk.photogallery.app.activity.detail.recommend.DetailRecommendAdapter
import com.fk.photogallery.app.activity.detail.recommend.DetailRecommendFragment
import com.fk.photogallery.app.activity.detail.recommend.DetailRecommendViewModel
import com.fk.photogallery.app.activity.main.home.recommend.RecommendFragment
import com.fk.photogallery.base.activity.BaseActivity
import com.fk.photogallery.base.adapter.BaseViewPagerAdapter
import com.fk.photogallery.base.adapter.BaseViewPagerAdapterK
import com.fk.photogallery.base.constutil.BaseConst
import com.fk.photogallery.base.model.dao.IntentParma
import com.fk.photogallery.base.model.dao.PhotoItem
import com.fk.photogallery.base.model.dao.TabMenu
import com.flyco.tablayout.SlidingTabLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

class PhotoDetailActivity : BaseActivity(){
    private lateinit var intentParam: IntentParma
    private lateinit var photoItem: PhotoItem
    private lateinit var adapter: BaseViewPagerAdapterK
    private lateinit var viewPager: ViewPager
    private lateinit var slidingTabLayout: SlidingTabLayout

    override fun setLayoutId(): Int = R.layout.activity_gallery_detail

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
        slidingTabLayout = findViewById(R.id.slidingTabLayout)
        viewPager = findViewById(R.id.viewPager)
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
        adapter = BaseViewPagerAdapterK(supportFragmentManager)
        adapter.addFragment(
            DetailRecommendFragment(TabMenu("${BaseConst.API}?${BaseConst.API_KEY}&q=${photoItem.tags}")),
            "推荐"
        )
        adapter.addFragment(
            DetailRecommendFragment(TabMenu("${BaseConst.API}?${BaseConst.API_KEY}&q=${photoItem.tags}")),
            "热门"
        )
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2
        slidingTabLayout.setViewPager(viewPager)
    }

    private fun initView() {
        displayWithUrl(R.id.iv_avatar,photoItem.userImageURL,R.mipmap.icon_default_avatar)
        displayWithUrl(R.id.iv_image,photoItem.largeImageURL,-1)
        setText(R.id.tv_theme,photoItem.tags)
        setText(R.id.tv_nickname,photoItem.user)
    }

}