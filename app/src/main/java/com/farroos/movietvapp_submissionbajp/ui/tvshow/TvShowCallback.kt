package com.farroos.movietvapp_submissionbajp.ui.tvshow

import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity

interface TvShowCallback {
    fun onItemClicked(data: TvShowEntity)
}