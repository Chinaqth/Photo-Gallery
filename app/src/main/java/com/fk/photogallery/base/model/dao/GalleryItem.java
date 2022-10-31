package com.fk.photogallery.base.model.dao;

import androidx.annotation.NonNull;

import com.fk.photogallery.core.model.dao.CoreBean;

import java.util.List;

public class GalleryItem extends CoreBean {
    private List<PhotoItem> hits;

    public List<PhotoItem> getList() {
        return hits;
    }

    public void setList(List<PhotoItem> list) {
        this.hits = list;
    }

    @NonNull
    @Override
    public String toString() {
        return "GalleryItem{" +
                "list=" + hits +
                '}';
    }
}
