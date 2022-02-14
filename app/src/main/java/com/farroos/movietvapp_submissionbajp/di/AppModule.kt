package com.farroos.movietvapp_submissionbajp.di

import android.app.Application
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.LocalDataSource
import com.farroos.movietvapp_submissionbajp.data.source.local.room.CatalogDao
import com.farroos.movietvapp_submissionbajp.data.source.local.room.CatalogDatabase
import com.farroos.movietvapp_submissionbajp.data.source.remote.RemoteDataSource
import com.farroos.movietvapp_submissionbajp.data.source.remote.api.ApiService
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    companion object {
        @Singleton
        @Provides
        fun provideCatalogDatabase(application: Application): CatalogDatabase =
            CatalogDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideCatalogDao(catalogDatabase: CatalogDatabase): CatalogDao =
            catalogDatabase.catalogDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(catalogDao: CatalogDao): LocalDataSource =
            LocalDataSource(catalogDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
            RemoteDataSource(apiService)

        @Singleton
        @Provides
        fun provideCatalogRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): CatalogRepository = CatalogRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepository: CatalogRepository): ViewModelFactory =
            ViewModelFactory(catalogRepository)
    }
}