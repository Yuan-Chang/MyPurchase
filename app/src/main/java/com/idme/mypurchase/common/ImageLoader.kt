package com.idme.mypurchase.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoader {
    fun load(url:String, imageView: ImageView){
        Picasso.get().load(url).into(imageView)
    }
}