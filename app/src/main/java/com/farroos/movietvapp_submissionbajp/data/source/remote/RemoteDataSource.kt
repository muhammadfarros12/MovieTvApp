package com.farroos.movietvapp_submissionbajp.data.source.remote

import com.farroos.movietvapp_submissionbajp.data.source.remote.api.ApiClient
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.MovieResponse
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.TvShowResponse
import com.farroos.movietvapp_submissionbajp.utility.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {

    suspend fun getMovies(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getMovie().await().result?.let {
            callback.onAllMovieReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getTvShow().await().result?.let {
            callback.onAllTvShowReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailMovie(movieId).await().let {
            callback.onMovieDetailReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        ApiClient.instance.getDetailTvShow(tvShowId).await().let {
            callback.onTvShowDetailReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    // Home
    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>)
    }

    // detail
    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: MovieResponse)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: TvShowResponse)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }
    }

}