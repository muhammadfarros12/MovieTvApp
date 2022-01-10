package com.farroos.movietvapp_submissionbajp.ui.tvshow

import androidx.lifecycle.ViewModel
import com.farroos.movietvapp_submissionbajp.data.MovieTvShowEntity
import com.farroos.movietvapp_submissionbajp.utility.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShow(): List<MovieTvShowEntity> = DataDummy.generateDummyTvShow()

}