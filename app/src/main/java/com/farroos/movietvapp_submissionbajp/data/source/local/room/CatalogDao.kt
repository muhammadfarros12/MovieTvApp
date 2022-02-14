package com.farroos.movietvapp_submissionbajp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity

@Dao
interface CatalogDao {

    @Query("SELECT * FROM movie_favorite")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_favorite")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie_favorite WHERE isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_favorite WHERE isFavorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie_favorite WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_show_favorite WHERE tvShowId = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShow(tvShows: List<TvShowEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movie: MovieEntity)

    @Update(entity = TvShowEntity::class)
    fun updateTvShow(tvShow: TvShowEntity)
}