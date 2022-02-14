package com.farroos.movietvapp_submissionbajp.ui.favorite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.databinding.ItemFavoriteBinding
import com.farroos.movietvapp_submissionbajp.ui.movie.MovieCallback
import com.farroos.movietvapp_submissionbajp.utility.constant.loadImage

class FavoriteMovieAdapter(private val callback: MovieCallback) :
    PagedListAdapter<MovieEntity, FavoriteMovieAdapter.ViewHolder>(
        DIFF_CALLBACK
    ) {
    inner class ViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                txtName.text = movie.name
                txtRate.text = movie.rate.toString()

                imgPoster.loadImage(
                    itemView.context.getString(R.string.url_poster, movie.poster), imgPoster
                )

                itemView.setOnClickListener {
                    callback.onItemClicked(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMovieAdapter.ViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieAdapter.ViewHolder, position: Int) {
        val favoriteMovie = getItem(position)
        if (favoriteMovie != null) {
            holder.bind(favoriteMovie)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}