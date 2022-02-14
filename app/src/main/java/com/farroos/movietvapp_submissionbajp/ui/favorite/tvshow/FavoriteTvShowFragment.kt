package com.farroos.movietvapp_submissionbajp.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.databinding.FragmentFavoriteTvShowBinding
import com.farroos.movietvapp_submissionbajp.ui.detail.DetailActivity
import com.farroos.movietvapp_submissionbajp.ui.favorite.FavoriteViewModel
import com.farroos.movietvapp_submissionbajp.ui.tvshow.TvShowCallback
import com.farroos.movietvapp_submissionbajp.utility.constant.TYPE_MOVIE
import com.farroos.movietvapp_submissionbajp.utility.constant.TYPE_TVSHOW
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteTvShowFragment : DaggerFragment(), TvShowCallback {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding as FragmentFavoriteTvShowBinding

    private var _viewModel: FavoriteViewModel? = null
    private val viewModel get() = _viewModel as FavoriteViewModel

    private var _mAdapter: FavoriteTvShowAdapter? = null
    private val mAdapter get() = _mAdapter as FavoriteTvShowAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)

        _mAdapter = FavoriteTvShowAdapter(this@FavoriteTvShowFragment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rcvFavoriteTvshow) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        activity?.let { setupViewModel(it) }
        observeFavoriteTvShow()
    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        _viewModel = ViewModelProvider(fragmentActivity, factory)[FavoriteViewModel::class.java]
    }

    private fun observeFavoriteTvShow() {
        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.rcvFavoriteTvshow.adapter?.let { adapter ->
                    when (adapter) {
                        is FavoriteTvShowAdapter -> {
                            if (it.isNullOrEmpty()) {
                                binding.rcvFavoriteTvshow.visibility = View.GONE
                            } else {
                                binding.rcvFavoriteTvshow.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onItemClicked(data: TvShowEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, data.tvShowId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
        )
    }

}