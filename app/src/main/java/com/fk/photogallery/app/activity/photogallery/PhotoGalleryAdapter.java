package com.fk.photogallery.app.activity.photogallery;

import androidx.lifecycle.ViewModelProvider;

import com.fk.photogallery.R;
import com.fk.photogallery.base.activity.BaseActivity;
import com.fk.photogallery.base.adapter.BaseQuickAdapter;
import com.fk.photogallery.base.adapter.BaseViewHolder;
import com.fk.photogallery.base.model.dao.PhotoItem;
import com.fk.photogallery.core.model.dao.CoreBean;

public class PhotoGalleryAdapter extends BaseQuickAdapter<BaseViewHolder> {

    private CoreBean data;

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_photo_gallery;
    }

    @Override
    protected void convert(BaseViewHolder holder, int position) {
        if (data == null || data.getHits() == null) {
            return;
        }
        PhotoItem photoItems = data.getHits().get(position);
        holder.displayWithUrl(R.id.iv_image, photoItems.getLargeImageURL(), R.mipmap.icon_load_fail);
    }

    public void updateData(CoreBean data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        if (data!= null) {
            return data.getHits().size();
        }
        return 0;
    }
}
