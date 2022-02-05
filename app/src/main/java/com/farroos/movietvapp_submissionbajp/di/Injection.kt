package com.farroos.movietvapp_submissionbajp.di

import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.remote.RemoteDataSource

object Injection {

    fun provideCatalogRepository(): CatalogRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogRepository.getInstance(remoteDataSource)
    }
}