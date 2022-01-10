package com.farroos.movietvapp_submissionbajp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.farroos.movietvapp_submissionbajp.data.MovieTvShowEntity
import com.farroos.movietvapp_submissionbajp.databinding.ActivityDetailBinding
import com.farroos.movietvapp_submissionbajp.databinding.ContentDetailBinding
import com.farroos.movietvapp_submissionbajp.utility.loadImage

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var detailContentBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = binding.detailContent
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_TVMOVIE)
            if (id != null) {
                viewModel.setSelectedMovieTvShow(id)
                detailMovieTvShow(viewModel.getDetailMovieTvShow())
            }
        }
    }

    private fun detailMovieTvShow(movieTvShowEntity: MovieTvShowEntity) {
        detailContentBinding.apply {
            txtActor.text = movieTvShowEntity.actor
            txtDescription.text = movieTvShowEntity.description
            txtDirector.text = movieTvShowEntity.director
            txtGenre.text = movieTvShowEntity.genre
            txtDuration.text = movieTvShowEntity.duration
            txtYear.text = movieTvShowEntity.year
            txtTitle.text = movieTvShowEntity.title
        }

        detailContentBinding.imgPoster.loadImage(movieTvShowEntity.imagePath)
    }

    companion object {
        const val EXTRA_TVMOVIE = "extra_tvmovie"
    }

}