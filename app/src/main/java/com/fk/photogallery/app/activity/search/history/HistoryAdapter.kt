package com.fk.photogallery.app.activity.search.history

import com.fk.photogallery.R
import com.fk.photogallery.base.adapter.recyclerview.ZAdapter
import com.fk.photogallery.base.adapter.recyclerview.ZViewHolder
import com.fk.photogallery.base.model.dao.History

class HistoryAdapter : ZAdapter<String, ZViewHolder> {

    constructor() : super(R.layout.item_history) {
    }
    override fun convert(holder: ZViewHolder, item: String) {
            holder.setText(R.id.tv_value,item)
    }
}