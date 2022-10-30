package com.fk.photogallery.core.model.dao;

import com.fk.photogallery.base.model.dao.PhotoItem;

import java.util.List;

public class CoreBean {
    private int total;
    private int totalHits;
    private boolean isPullRefresh = false;
    private int current_page;
    private int total_page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public boolean isPullRefresh() {
        return isPullRefresh;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public boolean isLastPage() {
        if (current_page == 0 || current_page == total_page) {
            return true;
        }
        return false;
    }
}
