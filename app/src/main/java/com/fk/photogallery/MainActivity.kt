package com.fk.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var iamge: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        iamge = findViewById(R.id.iv_image)

        button.setOnClickListener() {
            Glide.with(applicationContext)
                .load(R.mipmap.icon_placeholder)
                .placeholder(R.mipmap.icon_accost_list)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(iamge)
        }

    }
}
