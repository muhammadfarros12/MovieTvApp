package com.farroos.movietvapp_submissionbajp.ui.favorite

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.databinding.ActivityFavoriteBinding
import com.farroos.movietvapp_submissionbajp.viewmodel.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class FavoriteActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerFavoriteAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager2
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        setUpViewModel()

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.favorite)
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this@FavoriteActivity, factory)[FavoriteViewModel::class.java]
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.tab_3,
            R.string.tab_4,
        )
    }

}