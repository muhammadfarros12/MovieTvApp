package com.farroos.movietvapp_submissionbajp.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.farroos.movietvapp_submissionbajp.ui.favorite.movie.FavoriteMovieFragment
import com.farroos.movietvapp_submissionbajp.ui.favorite.tvshow.FavoriteTvShowFragment

class SectionPagerFavoriteAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }

}