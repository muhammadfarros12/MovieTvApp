package com.farroos.movietvapp_submissionbajp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.vo.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: CatalogRepository) : ViewModel() {
    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> = repository.getMovies()

    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getTvShow()
}