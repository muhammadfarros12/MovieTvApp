package com.farroos.movietvapp_submissionbajp.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.data.MovieTvShowEntity
import com.farroos.movietvapp_submissionbajp.databinding.ItemRecycleviewBinding
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listMovies = ArrayList<MovieTvShowEntity>()

    fun setMovie(movies: List<MovieTvShowEntity>?){
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(private val binding:ItemRecycleviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieTvShowEntity){
            with(binding){
                txtTitle.text = movie.title
                txtGenre.text = movie.genre
                txtDurasi.text = movie.duration

                Glide.with(itemView.context)
                    .load(movie.imagePath)
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TVMOVIE, movie.id)
                    itemView.context.startActivity(intent)
                }

            }
        }

    }
}