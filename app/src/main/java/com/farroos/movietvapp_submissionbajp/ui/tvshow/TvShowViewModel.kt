package com.farroos.movietvapp_submissionbajp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel

class TvShowViewModel(private val repository: CatalogRepository) : ViewModel() {
    fun getTvShow(): LiveData<List<DataModel>> = repository.getTvShow()

}