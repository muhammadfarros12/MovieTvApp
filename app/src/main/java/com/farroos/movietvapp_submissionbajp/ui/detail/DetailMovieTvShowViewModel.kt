package com.farroos.movietvapp_submissionbajp.ui.detail

import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import com.farroos.movietvapp_submissionbajp.data.MovieTvShowEntity
import com.farroos.movietvapp_submissionbajp.utility.DataDummy

class DetailMovieTvShowViewModel : ViewModel() {

    private lateinit var id: String

    private lateinit var movieTvShowEntity: MovieTvShowEntity

    fun setSelectedMovieTvShow(id: String) {
        this.id = id
    }

    fun getDetailMovieTvShow(): MovieTvShowEntity {
        val movieEntities = DataDummy.generateDummyMovie()
        for (movie in movieEntities) {
            if (movie.id == id) {
                movieTvShowEntity = movie
            }
        }

        val tvShowEntities = DataDummy.generateDummyTvShow()
        for (tvShow in tvShowEntities){
            if (tvShow.id == id){
                movieTvShowEntity = tvShow
            }
        }
        return movieTvShowEntity
    }

}