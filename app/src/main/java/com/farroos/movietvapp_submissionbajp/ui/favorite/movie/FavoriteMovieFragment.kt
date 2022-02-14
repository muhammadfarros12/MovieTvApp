package com.farroos.movietvapp_submissionbajp.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.databinding.FragmentFavoriteMovieBinding
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity
import com.farroos.movietvapp_submissionbajp.ui.favorite.FavoriteViewModel
import com.farroos.movietvapp_submissionbajp.ui.movie.MovieCallback
import com.farroos.movietvapp_submissionbajp.utility.constant.TYPE_MOVIE
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteMovieFragment : DaggerFragment(), MovieCallback {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding as FragmentFavoriteMovieBinding

    private var _viewModel: FavoriteViewModel? = null
    private val viewModel get() = _viewModel as FavoriteViewModel

    private var _mAdapter: FavoriteMovieAdapter? = null
    private val mAdapter get() = _mAdapter as FavoriteMovieAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)

        _mAdapter = FavoriteMovieAdapter(this@FavoriteMovieFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rcvFavoriteMovie){
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        activity?.let { setupViewModel(it) }
        observeFavoriteMovies()

    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        _viewModel = ViewModelProvider(fragmentActivity, factory)[FavoriteViewModel::class.java]
    }

    private fun observeFavoriteMovies(){
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.rcvFavoriteMovie.adapter?.let { adapter ->
                    when(adapter){
                        is FavoriteMovieAdapter -> {
                            if (it.isNullOrEmpty()){
                                binding.rcvFavoriteMovie.visibility = View.GONE
                            } else {
                                binding.rcvFavoriteMovie.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }

}