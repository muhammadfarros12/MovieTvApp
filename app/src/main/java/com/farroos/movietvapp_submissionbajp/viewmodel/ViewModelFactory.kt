package com.farroos.movietvapp_submissionbajp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailViewModel
import com.farroos.movietvapp_submissionbajp.ui.favorite.FavoriteViewModel
import com.farroos.movietvapp_submissionbajp.ui.home.HomeViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(
                catalogRepository
            ) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                catalogRepository
            ) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(
                catalogRepository
            ) as T
            else -> throw Throwable("Unknown View Model Class: ${modelClass.name}")
        }
    }

/*    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory = instance ?: synchronized(this){
            instance ?: ViewModelFactory(Injection.provideCatalogRepository())
        }
    }*/
}