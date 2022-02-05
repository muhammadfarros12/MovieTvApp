package com.farroos.movietvapp_submissionbajp.data.source

import androidx.lifecycle.LiveData
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel

interface CatalogDataSource {

    fun getMovies(): LiveData<List<DataModel>>

    fun getDetailMovie(movieId: Int): LiveData<DataModel>

    fun getTvShow(): LiveData<List<DataModel>>

    fun getDetailTvShow(tvShowId: Int): LiveData<DataModel>

}