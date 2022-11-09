package com.fk.photogallery.app.activity.photogallery;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;

import com.fk.photogallery.R;
import com.fk.photogallery.base.activity.BaseActivity;
import com.fk.photogallery.base.adapter.BaseQuickAdapter;
import com.fk.photogallery.base.adapter.BaseViewHolder;
import com.fk.photogallery.base.model.dao.PhotoItem;
import com.fk.photogallery.core.model.dao.CoreBean;

import java.util.List;

public class PhotoGalleryAdapter extends BaseQuickAdapter<BaseViewHolder> {

    private List<PhotoItem> data;

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_photo_gallery;
    }

    @Override
    protected void convert(BaseViewHolder holder, int position) {
        if (data == null || data.isEmpty()) {
            return;
        }
        PhotoItem photoItems = data.get(position);
        ImageView ivImage = holder.getView(R.id.iv_image);
        ViewGroup.LayoutParams layoutParams = ivImage.getLayoutParams();
        layoutParams.height = photoItems.getWebformatHeight();
        ivImage.setLayoutParams(layoutParams);
        holder.displayWithUrl(R.id.iv_image, photoItems.getWebformatURL(), R.mipmap.icon_load_fail);
    }

    public void updateData(List<PhotoItem> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        if (data!= null) {
            return data.size();
        }
        return 0;
    }
}
