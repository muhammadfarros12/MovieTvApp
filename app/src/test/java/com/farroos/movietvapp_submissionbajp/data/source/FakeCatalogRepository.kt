package com.farroos.movietvapp_submissionbajp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.farroos.movietvapp_submissionbajp.data.NetworkBoundResource
import com.farroos.movietvapp_submissionbajp.data.source.local.LocalDataSource
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.data.source.remote.ApiResponse
import com.farroos.movietvapp_submissionbajp.data.source.remote.RemoteDataSource
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.MovieResponse
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.TvShowResponse
import com.farroos.movietvapp_submissionbajp.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class FakeCatalogRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    CatalogDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<MovieResponse>?) {
                val movieList = ArrayList<MovieEntity>()
                if (data != null) {
                    for (item in data) {
                        val movie = MovieEntity(
                            null,
                            item.id,
                            item.title,
                            item.overview,
                            item.posterPath,
                            item.voteAverage,
                            item.releaseDate,
                            false
                        )
                        movieList.add(movie)
                    }
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = localDataSource.getDetailMovie(movieId)


    override fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(10)
                    setPageSize(10)
                }.build()
                return LivePagedListBuilder(localDataSource.getTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShow()

            override fun saveCallResult(data: List<TvShowResponse>?) {
                val tvShowList = ArrayList<TvShowEntity>()
                if (data != null) {
                    for (item in data) {
                        val tvShow = TvShowEntity(
                            null,
                            item.id,
                            item.name,
                            item.overview,
                            item.posterPath,
                            item.voteAverage,
                            item.firstAirDate,
                            false
                        )
                        tvShowList.add(tvShow)
                    }
                    localDataSource.insertTvShow(tvShowList)
                }
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity> =
        localDataSource.getDetailTvShow(tvShowId)


    override fun setFavoriteMovie(movie: MovieEntity) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteMovie(movie)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteTvShow(tvShow)
        }
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config =PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

}