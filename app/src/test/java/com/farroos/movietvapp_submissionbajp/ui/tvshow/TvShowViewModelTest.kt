package com.farroos.movietvapp_submissionbajp.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.ui.home.HomeViewModel
import com.farroos.movietvapp_submissionbajp.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(catalogRepository)
    }

    @Test
    fun getListMovies(){
        val dummyTvShow  = Resource.success(moviePagedList)
        `when`(dummyTvShow.data?.size).thenReturn(5)

        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow

        `when`(catalogRepository.getTvShow()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow().value?.data

        verify(catalogRepository).getTvShow()
        Assert.assertNotNull(tvShowEntity)
        Assert.assertEquals(5, tvShowEntity?.size)

        viewModel.getTvShow().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyTvShow)
    }
}