package com.farroos.movietvapp_submissionbajp.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel
import com.farroos.movietvapp_submissionbajp.databinding.FragmentMovieBinding
import com.farroos.movietvapp_submissionbajp.ui.DataCallback
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity
import com.farroos.movietvapp_submissionbajp.utility.TYPE_MOVIE
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory

class MovieFragment : Fragment(), DataCallback {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding

    private var _viewModel: MovieViewModel? = null
    private val viewModel get() = _viewModel as MovieViewModel


    private var _mAdapter: MovieAdapter? = null
    private val mAdapter get() = _mAdapter as MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        _mAdapter = MovieAdapter(this@MovieFragment)

        val viewModelFactory = ViewModelFactory.getInstance()
        _viewModel = activity?.let { ViewModelProvider(it, viewModelFactory) }
            ?.get(MovieViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        viewModel.getMovie().observe(viewLifecycleOwner, {
            mAdapter.setMovie(it)
            Log.i("SetDataMovie", "onViewCreated: $it")
        })

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(data: DataModel) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, data.id)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }

}
