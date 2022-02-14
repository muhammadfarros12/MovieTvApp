package com.farroos.movietvapp_submissionbajp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.room.CatalogDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val catalogDao: CatalogDao) {

    fun getMovie(): DataSource.Factory<Int, MovieEntity> = catalogDao.getMovie()

    fun getTvShow(): DataSource.Factory<Int, TvShowEntity> = catalogDao.getTvShow()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = catalogDao.getFavoriteMovie()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = catalogDao.getFavoriteTvShow()

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = catalogDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity> =
        catalogDao.getDetailTvShowById(tvShowId)

    fun insertMovie(movies: List<MovieEntity>) = catalogDao.insertMovies(movies)

    fun insertTvShow(tvShow: List<TvShowEntity>) = catalogDao.insertTvShow(tvShow)

    fun setFavoriteMovie(movie: MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        catalogDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        catalogDao.updateTvShow(tvShow)
    }
}