package com.farroos.movietvapp_submissionbajp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.di.Injection
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailViewModel
import com.farroos.movietvapp_submissionbajp.ui.movie.MovieViewModel
import com.farroos.movietvapp_submissionbajp.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val catalogRepository: CatalogRepository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(catalogRepository) as T
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(catalogRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(catalogRepository) as T
            else -> throw Throwable("Unknown View Model Class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory = instance ?: synchronized(this){
            instance ?: ViewModelFactory(Injection.provideCatalogRepository())
        }
    }
}