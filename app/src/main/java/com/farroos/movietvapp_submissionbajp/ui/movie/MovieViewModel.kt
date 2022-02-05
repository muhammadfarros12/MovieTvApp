package com.farroos.movietvapp_submissionbajp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel

class MovieViewModel(
    private val repository: CatalogRepository,
) : ViewModel() {
    fun getMovie(): LiveData<List<DataModel>> = repository.getMovies()
}