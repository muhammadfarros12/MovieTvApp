package com.farroos.movietvapp_submissionbajp.ui.tvshow

import android.content.Intent
import android.gesture.GestureLibraries
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farroos.movietvapp_submissionbajp.data.MovieTvShowEntity
import com.farroos.movietvapp_submissionbajp.databinding.ItemRecycleviewBinding
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val listTvShows = ArrayList<MovieTvShowEntity>()

    fun setTvShows(tvShow: List<MovieTvShowEntity>?){
        if (tvShow != null){
            this.listTvShows.clear()
            this.listTvShows.addAll(tvShow)
        }
    }


    inner class ViewHolder(private val binding: ItemRecycleviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: MovieTvShowEntity){
            with(binding){
                txtTitle.text = tvShow.title
                txtDurasi.text = tvShow.duration
                txtGenre.text = tvShow.genre

                Glide.with(itemView.context)
                    .load(tvShow.imagePath)
                    .into(binding.imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TVMOVIE, tvShow.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.ViewHolder {
        val binding = ItemRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.ViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShows.size
}