package com.farroos.movietvapp_submissionbajp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<DataModel> =
        catalogRepository.getDetailMovie(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<DataModel> =
        catalogRepository.getDetailTvShow(tvShowId)

}