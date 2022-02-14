package com.farroos.movietvapp_submissionbajp.di.home

import com.farroos.movietvapp_submissionbajp.ui.movie.MovieFragment
import com.farroos.movietvapp_submissionbajp.ui.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment(): TvShowFragment

}