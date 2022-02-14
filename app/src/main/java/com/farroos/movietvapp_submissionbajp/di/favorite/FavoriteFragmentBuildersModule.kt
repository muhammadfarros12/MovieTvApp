package com.farroos.movietvapp_submissionbajp.di.favorite

import com.farroos.movietvapp_submissionbajp.ui.favorite.movie.FavoriteMovieFragment
import com.farroos.movietvapp_submissionbajp.ui.favorite.tvshow.FavoriteTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMovieFragment(): FavoriteMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowFragment(): FavoriteTvShowFragment
}