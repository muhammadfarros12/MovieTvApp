package com.farroos.movietvapp_submissionbajp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val catalog: CatalogRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = catalog.getFavoriteMovie()

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = catalog.getFavoriteTvShow()

}