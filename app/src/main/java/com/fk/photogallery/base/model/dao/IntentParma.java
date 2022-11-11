package com.fk.photogallery.base.model.dao;

import java.io.Serializable;

public class IntentParma implements Serializable {

    private PhotoItem photoItem;

    public PhotoItem getPhotoItem() {
        return photoItem;
    }

    public void setPhotoItem(PhotoItem photoItem) {
        this.photoItem = photoItem;
    }
}
