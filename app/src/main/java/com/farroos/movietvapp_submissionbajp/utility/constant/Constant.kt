package com.farroos.movietvapp_submissionbajp.utility.constant

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farroos.movietvapp_submissionbajp.R


fun ImageView.loadImage(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions.placeholderOf(R.drawable.ic_refresh_black)
                .error(R.drawable.ic_error)
        )
        .into(imageView)
}

const val DELAY_TIME: Long = 3000

const val TYPE_MOVIE = "type_movie"
const val TYPE_TVSHOW = "type_tvshow"