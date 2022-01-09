package com.farroos.movietvapp_submissionbajp.ui.movie

import androidx.lifecycle.ViewModel
import com.farroos.movietvapp_submissionbajp.data.MovieTvShowEntity
import com.farroos.movietvapp_submissionbajp.utility.DataDummy

class MovieViewModel: ViewModel() {
    fun getMovie(): List<MovieTvShowEntity> = DataDummy.generateDummyMovie()
}