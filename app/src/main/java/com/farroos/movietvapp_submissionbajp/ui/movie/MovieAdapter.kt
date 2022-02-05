package com.farroos.movietvapp_submissionbajp.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel
import com.farroos.movietvapp_submissionbajp.databinding.ItemRecycleviewBinding
import com.farroos.movietvapp_submissionbajp.ui.DataCallback
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity
import com.farroos.movietvapp_submissionbajp.utility.loadImage

class MovieAdapter(private val callback: DataCallback) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listMovies = ArrayList<DataModel>()

    fun setMovie(movies: List<DataModel>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(private val binding: ItemRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: DataModel) {
            with(binding) {
                txtTitle.text = movie.name
                txtRealeaseDate.text = movie.realeaseDate
                voteAverage.text = movie.rate.toString()

                /*Glide.with(itemView.context)
                    .load(movie.imagePath)
                    .into(imgPoster)*/
                imgPoster.loadImage(
                    itemView.context.getString(R.string.url_poster, movie.poster), imgPoster
                )

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TVMOVIE, movie.id)
                    itemView.context.startActivity(intent)
                }

                cardView.setOnClickListener {
                    callback.onItemClicked(movie)
                }

            }

        }

    }
}