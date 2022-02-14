package com.farroos.movietvapp_submissionbajp.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.databinding.FragmentMovieBinding
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity
import com.farroos.movietvapp_submissionbajp.ui.home.HomeViewModel
import com.farroos.movietvapp_submissionbajp.utility.constant.TYPE_MOVIE
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory
import com.farroos.movietvapp_submissionbajp.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieFragment : DaggerFragment(), MovieCallback {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding

    private var _viewModel: HomeViewModel? = null
    private val viewModel get() = _viewModel as HomeViewModel

    private var _mAdapter: MovieAdapter? = null
    private val mAdapter get() = _mAdapter as MovieAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        _mAdapter = MovieAdapter(this@MovieFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter

        }

        activity?.let { setupViewModel(it) }
        observeMovie()

    }


    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        _viewModel = ViewModelProvider(fragmentActivity, factory)[HomeViewModel::class.java]
    }

    private fun observeMovie() {
        viewModel.getMovie().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvMovie.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "Check your internet connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }

}
