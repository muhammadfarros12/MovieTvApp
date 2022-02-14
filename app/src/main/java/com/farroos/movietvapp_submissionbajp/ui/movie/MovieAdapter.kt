package com.farroos.movietvapp_submissionbajp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.databinding.ItemRecycleviewBinding
import com.farroos.movietvapp_submissionbajp.utility.constant.loadImage

class MovieAdapter(private val callback: MovieCallback) :
    PagedListAdapter<MovieEntity, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }

    }

    inner class ViewHolder(private val binding: ItemRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                txtTitle.text = movie.name
                txtRealeaseDate.text = movie.realeaseDate
                voteAverage.text = movie.rate.toString()

                imgPoster.loadImage(
                    itemView.context.getString(R.string.url_poster, movie.poster), imgPoster
                )

                /*itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TVMOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                }
*/
                itemView.setOnClickListener {
                    callback.onItemClicked(movie)
                }

            }

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