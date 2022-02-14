package com.farroos.movietvapp_submissionbajp.ui.movie

import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(data: MovieEntity)
}