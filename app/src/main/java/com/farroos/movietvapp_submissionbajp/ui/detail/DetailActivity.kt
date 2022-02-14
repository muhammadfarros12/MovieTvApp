package com.farroos.movietvapp_submissionbajp.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.databinding.ActivityDetailBinding
import com.farroos.movietvapp_submissionbajp.databinding.ContentDetailBinding
import com.farroos.movietvapp_submissionbajp.utility.constant.TYPE_MOVIE
import com.farroos.movietvapp_submissionbajp.utility.constant.TYPE_TVSHOW
import com.farroos.movietvapp_submissionbajp.utility.constant.loadImage
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailContentBinding: ContentDetailBinding

    private var _viewModel: DetailViewModel? = null
    private val viewModel get() = _viewModel as DetailViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = binding.detailContent

        setContentView(binding.root)

        setUpViewModel()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail)

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            viewModel.getMovieDetail(id).observe(this, Observer {
                detailMovieTvShow(it, null)
            })
        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            viewModel.getTvShowDetail(id).observe(this) {
                detailMovieTvShow(null, it)
            }
        }
    }

    private fun setUpViewModel() {
        _viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    private fun detailMovieTvShow(movie: MovieEntity?, tvShow: TvShowEntity?) {
        detailContentBinding.apply {
            txtTitle.text = movie?.name ?: tvShow?.name
            txtDescription.text = movie?.desc ?: tvShow?.desc
            txtRate.text = (movie?.rate ?: tvShow?.rate).toString()
            txtRealease.text = movie?.realeaseDate ?: tvShow?.realeaseDate

            val statusFavorite = movie?.isFavorite ?: tvShow?.isFavorite

            statusFavorite?.let { status ->
                setFavoriteState(status)
            }

            tglFavorite.setOnClickListener {
                setFavorite(movie, tvShow)
            }

            imgPoster.loadImage(
                getString(R.string.url_poster, movie?.poster), imgPoster
            )

            imgPoster.loadImage(
                getString(R.string.url_poster, tvShow?.poster), imgPoster
            )

        }

    }

    private fun setFavoriteState(status: Boolean) {
        if (status) {
            detailContentBinding.tglFavorite.setImageResource(R.drawable.ic_favorite_red)
        } else {
            detailContentBinding.tglFavorite.setImageResource(R.drawable.ic_favorite_grey)
        }
    }

    private fun snackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun setFavorite(movie: MovieEntity?, tvShow: TvShowEntity?) {
        if (movie != null) {
            if (movie.isFavorite) {
                snackBar("${movie.name} Removed from favorite")
            } else {
                snackBar("${movie.name} Added to favorite")
            }
            viewModel.setFavoriteMovie(movie)
        } else if (tvShow != null) {
            if (tvShow.isFavorite) {
                snackBar("${tvShow.name}  Removed from favorite")
            } else {
                snackBar("${tvShow.name}  Added to favorite")
            }
            viewModel.setFavoriteTvShow(tvShow)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_TVMOVIE = "extra_tvmovie"

        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_DATA = "extra_data"
    }

}