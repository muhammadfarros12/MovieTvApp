package com.farroos.movietvapp_submissionbajp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {
    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        catalogRepository.getDetailMovie(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
        catalogRepository.getDetailTvShow(tvShowId)

    fun setFavoriteMovie(movie: MovieEntity) {
        catalogRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        catalogRepository.setFavoriteTvShow(tvShow)
    }


}