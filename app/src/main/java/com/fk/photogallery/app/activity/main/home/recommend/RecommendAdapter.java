package com.fk.photogallery.app.activity.main.home.recommend;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.fk.photogallery.R;
import com.fk.photogallery.app.activity.detail.PhotoDetailActivity;
import com.fk.photogallery.app.dialog.PhotoDetailDialog;
import com.fk.photogallery.base.adapter.recyclerview.ZAdapter;
import com.fk.photogallery.base.adapter.recyclerview.animation.AlphaInAnimation;
import com.fk.photogallery.base.adapter.recyclerview.listener.OnItemClickListener;
import com.fk.photogallery.base.adapter.recyclerview.listener.OnItemLongClickListener;
import com.fk.photogallery.base.model.dao.IntentParma;
import com.fk.photogallery.base.model.dao.PhotoItem;

import com.fk.photogallery.base.adapter.recyclerview.ZViewHolder;
import com.fk.photogallery.base.utils.FunctionUtils;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends ZAdapter<PhotoItem, ZViewHolder> {

    public RecommendAdapter() {
        super(R.layout.item_photo_gallery);
        setAdapterAnimation(new AlphaInAnimation());
        setOnItemClickListener(onItemClickListener);
        setOnItemLongClickListener(onItemLongClickListener);
    }

    @Override
    protected void convert(@NonNull ZViewHolder holder, PhotoItem item) {
        if (item == null) {
            return;
        }
        ImageView ivImage = holder.getView(R.id.iv_image);
        ViewGroup.LayoutParams layoutParams = ivImage.getLayoutParams();
        layoutParams.height = item.getWebformatHeight() + 200;
        ivImage.setLayoutParams(layoutParams);
        ivImage.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_recyclerview));
        holder.displayWithUrl(R.id.iv_image, item.getWebformatURL(), -1);

        holder.setText(R.id.tv_theme, item.getTags());
        holder.displayWithUrl(R.id.iv_uer_avatar, item.getUserImageURL(), R.mipmap.icon_placeholder);
        holder.setText(R.id.tv_nickname, item.getUser());
        holder.setText(R.id.tv_like, "" + item.getLikes());
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(@NonNull ZAdapter<?, ?> adapter, @NonNull View view, int position) {
            PhotoItem item = (PhotoItem) adapter.getItem(position);
            IntentParma intentParma = new IntentParma();
            intentParma.setPhotoItem(item);
            FunctionUtils.INSTANCE.goTo(PhotoDetailActivity.class, intentParma, false);
        }
    };

    OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(@NonNull ZAdapter<?, ?> adapter, @NonNull View view, int position) {
            Log.d("QQQ","onItemLongClick");
            PhotoDetailDialog photoDetailDialog = new PhotoDetailDialog(getContext(), (PhotoItem) adapter.getItem(position));
            photoDetailDialog.show();
            return true;
        }
    };
}
