package com.fk.photogallery.app.activity.photogallery;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.fk.photogallery.R;
import com.fk.photogallery.base.activity.BaseActivity;
import com.fk.photogallery.base.adapter.BaseQuickAdapter;
import com.fk.photogallery.base.adapter.BaseViewHolder;
import com.fk.photogallery.base.model.dao.GalleryItem;
import com.fk.photogallery.base.model.dao.PhotoItem;
import com.fk.photogallery.core.model.dao.CoreBean;

import java.util.List;

public class PhotoGalleryAdapter extends BaseQuickAdapter<BaseViewHolder> {

    private final BaseActivity activity;
    private final GalleryViewModel galleryViewModel;

    public PhotoGalleryAdapter(BaseActivity activity) {
        this.activity = activity;
        galleryViewModel = new ViewModelProvider(activity).get(GalleryViewModel.class);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_photo_gallery;
    }

    @Override
    protected void convert(BaseViewHolder holder, int position) {
        CoreBean item = galleryViewModel.getPhotoItem().getValue();
        if (item == null || item.getHits() == null) {
            return;
        }
        PhotoItem photoItems = item.getHits().get(position);
        holder.displayWithUrl(R.id.iv_image, photoItems.getLargeImageURL(), R.mipmap.icon_placeholder);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
