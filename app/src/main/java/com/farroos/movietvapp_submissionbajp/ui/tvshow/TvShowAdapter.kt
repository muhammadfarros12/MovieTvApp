package com.farroos.movietvapp_submissionbajp.ui.tvshow

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

class TvShowAdapter(private val callback: DataCallback) :
    RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val listTvShows = ArrayList<DataModel>()

    fun setTvShows(tvShow: List<DataModel>?) {
        if (tvShow != null) {
            this.listTvShows.clear()
            this.listTvShows.addAll(tvShow)
            notifyDataSetChanged()
        }
    }


    inner class ViewHolder(private val binding: ItemRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: DataModel) {
            with(binding) {
                txtTitle.text = tvShow.name
                txtRealeaseDate.text = tvShow.realeaseDate
                voteAverage.text = tvShow.rate.toString()

                /*Glide.with(itemView.context)
                    .load(tvShow.imagePath)
                    .into(binding.imgPoster)*/

                imgPoster.loadImage(
                    itemView.context.getString(R.string.url_poster, tvShow.poster),
                    imgPoster
                )

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TVMOVIE, tvShow.id)
                    itemView.context.startActivity(intent)
                }

                cardView.setOnClickListener {
                    callback.onItemClicked(tvShow)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.ViewHolder {
        val binding =
            ItemRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShows.size
}