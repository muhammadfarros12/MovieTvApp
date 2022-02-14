package com.farroos.movietvapp_submissionbajp.ui.tvshow

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
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.databinding.FragmentTvShowBinding
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity
import com.farroos.movietvapp_submissionbajp.ui.home.HomeViewModel
import com.farroos.movietvapp_submissionbajp.utility.constant.TYPE_TVSHOW
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory
import com.farroos.movietvapp_submissionbajp.vo.Status
import dagger.Module
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@Module
class TvShowFragment : DaggerFragment(), TvShowCallback {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: HomeViewModel? = null
    private val viewModel get() = _viewModel as HomeViewModel

    private var _mAdapter: TvShowAdapter? = null
    private val mAdapter get() = _mAdapter as TvShowAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        _mAdapter = TvShowAdapter(this@TvShowFragment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        activity?.let { setupViewModel(it) }
        observeTvShow()
    }

    private fun setUpRecyclerView() {
        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        _viewModel = ViewModelProvider(fragmentActivity, factory)[HomeViewModel::class.java]
    }

    private fun observeTvShow() {
        viewModel.getTvShow().observe(viewLifecycleOwner, Observer { listTvShow ->
            if (listTvShow != null) {
                when (listTvShow.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvTvShow.adapter?.let { adapter ->
                            when (adapter) {
                                is TvShowAdapter -> {
                                    adapter.submitList(listTvShow.data)
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

    override fun onItemClicked(data: TvShowEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, data.tvShowId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
        )
    }

}