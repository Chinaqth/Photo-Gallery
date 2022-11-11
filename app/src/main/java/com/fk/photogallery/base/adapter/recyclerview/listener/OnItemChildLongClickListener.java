package com.fk.photogallery.base.adapter.recyclerview.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.fk.photogallery.base.adapter.recyclerview.ZAdapter;


public interface OnItemChildLongClickListener {

    boolean onItemChildLongClick(@NonNull ZAdapter<?,?> adapter, @NonNull View view, int position);
}
