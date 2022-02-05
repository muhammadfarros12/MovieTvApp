package com.farroos.movietvapp_submissionbajp.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel
import com.farroos.movietvapp_submissionbajp.databinding.FragmentTvShowBinding
import com.farroos.movietvapp_submissionbajp.ui.DataCallback
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity
import com.farroos.movietvapp_submissionbajp.utility.TYPE_TVSHOW
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory


class TvShowFragment : Fragment(), DataCallback {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: TvShowViewModel? = null
    private val viewModel get() = _viewModel as TvShowViewModel

    private var _mAdapter: TvShowAdapter? = null
    private val mAdapter get() = _mAdapter as TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        _mAdapter = TvShowAdapter(this@TvShowFragment)

        val viewModelFactory = ViewModelFactory.getInstance()
        _viewModel = activity?.let { ViewModelProvider(it, viewModelFactory) }
            ?.get(TvShowViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]
            val tvShow = viewModel.getTvShow()

            val tvShowAdapter = TvShowAdapter()
            //tvShowAdapter.setTvShows(tvShow)*/

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        viewModel.getTvShow().observe(viewLifecycleOwner, {
            mAdapter.setTvShows(it)
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
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
        )
    }

}