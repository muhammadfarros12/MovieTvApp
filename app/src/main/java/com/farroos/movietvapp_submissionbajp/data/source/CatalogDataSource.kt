package com.farroos.movietvapp_submissionbajp.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.vo.Resource

interface CatalogDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>

    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity>

    fun setFavoriteMovie(movie: MovieEntity)

    fun setFavoriteTvShow(tvShow: TvShowEntity)

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

}