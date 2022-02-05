package com.farroos.movietvapp_submissionbajp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel
import com.farroos.movietvapp_submissionbajp.databinding.ActivityDetailBinding
import com.farroos.movietvapp_submissionbajp.databinding.ContentDetailBinding
import com.farroos.movietvapp_submissionbajp.utility.TYPE_MOVIE
import com.farroos.movietvapp_submissionbajp.utility.TYPE_TVSHOW
import com.farroos.movietvapp_submissionbajp.utility.loadImage
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailContentBinding: ContentDetailBinding

    private var _viewModel: DetailViewModel? = null
    private val viewModel get() = _viewModel as DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = binding.detailContent

        val viewModelFactory = ViewModelFactory.getInstance()
        _viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showType()

    }

    private fun showType() {
        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            viewModel.getMovieDetail(id).observe(this, Observer {
                detailMovieTvShow(it)
            })
        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            viewModel.getTvShowDetail(id).observe(this, Observer {
                detailMovieTvShow(it)
            })
        }
    }

    private fun detailMovieTvShow(data: DataModel) {
        detailContentBinding.apply {
            txtTitle.text = data.name
            txtDescription.text = data.desc
            txtRate.text = data.rate.toString()
            txtRealease.text = data.realeaseDate

            imgPoster.loadImage(
                getString(R.string.url_poster, data.poster), imgPoster
            )

        }

        // detailContentBinding.imgPoster.loadImage(R.string.url_poster)
    }

    companion object {
        const val EXTRA_TVMOVIE = "extra_tvmovie"

        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_DATA = "extra_data"
    }

}