package com.farroos.movietvapp_submissionbajp.di

import com.farroos.movietvapp_submissionbajp.di.favorite.FavoriteFragmentBuildersModule
import com.farroos.movietvapp_submissionbajp.di.home.HomeFragmentBuilderModule
import com.farroos.movietvapp_submissionbajp.ui.favorite.FavoriteActivity
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity
import com.farroos.movietvapp_submissionbajp.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
 @ContributesAndroidInjector(modules = [HomeFragmentBuilderModule::class])
 abstract fun contributeHomeActivity(): HomeActivity

 @ContributesAndroidInjector
 abstract fun contributeDetailActivity(): DetailActivity

 @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
 abstract fun contributeFavoriteActivity(): FavoriteActivity
}