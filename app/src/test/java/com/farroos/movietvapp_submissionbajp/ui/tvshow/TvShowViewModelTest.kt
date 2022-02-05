package com.farroos.movietvapp_submissionbajp.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel
import com.farroos.movietvapp_submissionbajp.utility.datadummy.DataDummyModelTvShow
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private val dummyTvShow = DataDummyModelTvShow.generateModelTvShow()

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<DataModel>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogRepository)
    }

    @Test
    fun getListTvShow() {
        val tvShow = MutableLiveData<List<DataModel>>()
        tvShow.value = dummyTvShow

        `when`(catalogRepository.getTvShow()).thenReturn(tvShow)

        val dataListTvShow = viewModel.getTvShow().value

        verify(catalogRepository).getTvShow()
        assertNotNull(dataListTvShow)
        assertEquals(2, dataListTvShow?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}