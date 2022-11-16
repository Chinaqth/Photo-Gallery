package com.fk.photogallery.base.model.dao;

/**
 * 用于Activity和其管理的Fragment 之间通信的类
 */
public class CustomBus {
    private String keyword; //搜索界面传递关键词

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
