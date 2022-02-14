package com.farroos.movietvapp_submissionbajp.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farroos.movietvapp_submissionbajp.data.source.remote.api.ApiService
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.MovieResponse
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.TvShowResponse
import com.farroos.movietvapp_submissionbajp.utility.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val catalogApiService: ApiService) {

    fun getMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = catalogApiService.getMovie().await()
                resultMovieResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieResponse
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShowResponse = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = catalogApiService.getTvShow().await()
                resultTvShowResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTvShowResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvShowResponse
    }

/*

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
*/

}