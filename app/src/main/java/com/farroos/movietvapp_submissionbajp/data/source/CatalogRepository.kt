package com.farroos.movietvapp_submissionbajp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farroos.movietvapp_submissionbajp.data.source.remote.RemoteDataSource
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.MovieResponse
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CatalogRepository(private val remoteDataSource: RemoteDataSource) :
    CatalogDataSource {

    override fun getMovies(): LiveData<List<DataModel>> {
        val listMovieResult = MutableLiveData<List<DataModel>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovies(object : RemoteDataSource.LoadMovieCallback {
                override fun onAllMovieReceived(movieResponse: List<MovieResponse>) {
                    val movieList = ArrayList<DataModel>()
                    for (response in movieResponse) {
                        val movie = DataModel(
                            response.id,
                            response.title,
                            response.overview,
                            response.posterPath,
                            response.voteAverage,
                            response.releaseDate
                        )
                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }

            })
        }

        return listMovieResult
    }

    override fun getDetailMovie(movieId: Int): LiveData<DataModel> {
        val detailMovie = MutableLiveData<DataModel>()

        CoroutineScope(IO).launch {
            remoteDataSource.getMovieDetail(
                movieId,
                object : RemoteDataSource.LoadMovieDetailCallback {
                    override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                        val getDetail = DataModel(
                            movieResponse.id,
                            movieResponse.title,
                            movieResponse.overview,
                            movieResponse.posterPath,
                            movieResponse.voteAverage,
                            movieResponse.releaseDate
                        )
                        detailMovie.postValue(getDetail)
                    }

                })
        }

        return detailMovie
    }

    override fun getTvShow(): LiveData<List<DataModel>> {
        val listTvShowResult = MutableLiveData<List<DataModel>>()

        CoroutineScope(IO).launch {
            remoteDataSource.getTvShow(object : RemoteDataSource.LoadTvShowCallback {
                override fun onAllTvShowReceived(tvShowResponse: List<TvShowResponse>) {
                    val tvShowList = ArrayList<DataModel>()
                    for (response in tvShowResponse) {
                        val tvShow = DataModel(
                            response.id,
                            response.name,
                            response.overview,
                            response.posterPath,
                            response.voteAverage,
                            response.firstAirDate
                        )
                        tvShowList.add(tvShow)
                    }
                    listTvShowResult.postValue(tvShowList)
                }

            })
        }

        return listTvShowResult
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<DataModel> {
        val detailTvShow = MutableLiveData<DataModel>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowDetail(
                tvShowId,
                object : RemoteDataSource.LoadTvShowDetailCallback {
                    override fun onTvShowDetailReceived(tvShowResponse: TvShowResponse) {
                        val getTvShow = DataModel(
                            tvShowResponse.id,
                            tvShowResponse.name,
                            tvShowResponse.overview,
                            tvShowResponse.posterPath,
                            tvShowResponse.voteAverage,
                            tvShowResponse.firstAirDate
                        )
                        detailTvShow.postValue(getTvShow)
                    }

                })
        }
        return detailTvShow
    }

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(remoteDataSource)
            }
    }
}