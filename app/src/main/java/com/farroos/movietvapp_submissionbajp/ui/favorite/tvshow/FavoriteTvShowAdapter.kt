package com.farroos.movietvapp_submissionbajp.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.databinding.ItemFavoriteBinding
import com.farroos.movietvapp_submissionbajp.ui.movie.MovieCallback
import com.farroos.movietvapp_submissionbajp.ui.tvshow.TvShowCallback
import com.farroos.movietvapp_submissionbajp.utility.constant.loadImage

class FavoriteTvShowAdapter(private val callback: TvShowCallback) : PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.ViewHolder>(
    DIFF_CALLBACK
) {
    inner class ViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                txtName.text = tvShow.name
                txtRate.text = tvShow.rate.toString()

                imgPoster.loadImage(
                    itemView.context.getString(R.string.url_poster, tvShow.poster), imgPoster
                )

                itemView.setOnClickListener {
                    callback.onItemClicked(tvShow)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteTvShowAdapter.ViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteTvShowAdapter.ViewHolder, position: Int) {
        val favoriteTvShow = getItem(position)
        if (favoriteTvShow != null) {
            holder.bind(favoriteTvShow)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}