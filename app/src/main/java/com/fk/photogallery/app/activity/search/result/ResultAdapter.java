package com.fk.photogallery.app.activity.search.result;

import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.fk.photogallery.R;
import com.fk.photogallery.app.activity.detail.PhotoDetailActivity;
import com.fk.photogallery.base.adapter.recyclerview.ZAdapter;
import com.fk.photogallery.base.adapter.recyclerview.ZViewHolder;
import com.fk.photogallery.base.adapter.recyclerview.animation.AlphaInAnimation;
import com.fk.photogallery.base.adapter.recyclerview.listener.OnItemClickListener;
import com.fk.photogallery.base.model.dao.IntentParma;
import com.fk.photogallery.base.model.dao.PhotoItem;
import com.fk.photogallery.base.utils.FunctionUtils;

public class ResultAdapter extends ZAdapter<PhotoItem, ZViewHolder> {

    public ResultAdapter() {
        super(R.layout.item_gallery);
        setAdapterAnimation(new AlphaInAnimation());
        setOnItemClickListener(onItemClickListener);
    }

    @Override
    protected void convert(@NonNull ZViewHolder holder, PhotoItem item) {
        if (item == null) {
            return;
        }
        ImageView ivImage = holder.getView(R.id.iv_image);
        ivImage.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_recyclerview));
        holder.displayWithUrl(R.id.iv_image, item.getWebformatURL(), -1);

        holder.setText(R.id.tv_title, item.getTags());
        holder.displayWithUrl(R.id.iv_avatar, item.getUserImageURL(), R.mipmap.icon_placeholder);
        holder.setText(R.id.tv_nickname, item.getUser());
        holder.setText(R.id.tv_like, "" + item.getLikes());
    }

    OnItemClickListener onItemClickListener = (adapter, view, position) -> {
        PhotoItem item = (PhotoItem) adapter.getItem(position);
        IntentParma intentParma = new IntentParma();
        intentParma.setPhotoItem(item);
        FunctionUtils.INSTANCE.goTo(PhotoDetailActivity.class, intentParma, false);
    };
}
