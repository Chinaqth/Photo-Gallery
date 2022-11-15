package com.fk.photogallery.base.adapter

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.fk.photogallery.base.fragment.BaseFragment
import com.fk.photogallery.base.fragment.BaseFragmentK
import java.util.ArrayList

class BaseViewPagerAdapterK(@NonNull fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    private var lists: MutableList<BaseFragmentK> = ArrayList()
    private var titles: MutableList<String> = ArrayList()

    fun addFragment(fragment: BaseFragmentK, title: String) {
        lists.add(fragment)
        titles.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return lists.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles.get(position)
    }

    override fun getCount(): Int {
        return lists.size
    }
}