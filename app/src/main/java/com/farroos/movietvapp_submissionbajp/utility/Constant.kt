package com.farroos.movietvapp_submissionbajp.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadImage(url: Int?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

const val DELAY_TIME: Long = 3000