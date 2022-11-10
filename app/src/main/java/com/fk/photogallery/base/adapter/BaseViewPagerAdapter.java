package com.fk.photogallery.base.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.fk.photogallery.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class BaseViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> lists;
    private List<String> titles;

    public BaseViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        lists = new ArrayList<>();
        titles = new ArrayList<>();
    }

    public void addFragment(BaseFragment fragment, String title) {
        lists.add(fragment);
        titles.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    public List<BaseFragment> getFragmentLists() {
        return lists;
    }

    public void setFragmentLists(List<BaseFragment> lists) {
        this.lists = lists;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}
