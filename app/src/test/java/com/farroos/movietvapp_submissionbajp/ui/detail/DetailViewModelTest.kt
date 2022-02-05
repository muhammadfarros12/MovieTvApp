package com.farroos.movietvapp_submissionbajp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel
import androidx.lifecycle.Observer
import com.farroos.movietvapp_submissionbajp.utility.datadummy.DataDummyModelMovie
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
class DetailViewModelTest {

    private val dummyMovie = DataDummyModelMovie.generateDummyModelMovie()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShow = DataDummyModelTvShow.generateModelTvShow()[0]
    private val tvShowId = dummyTvShow.id

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<DataModel>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<DataModel>()
        movieDummy.value = dummyMovie

        `when`(catalogRepository.getDetailMovie(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId).value as DataModel

        assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData.id)
        assertEquals(dummyMovie.name, movieData.name)
        assertEquals(dummyMovie.desc, movieData.desc)
        assertEquals(dummyMovie.poster, movieData.poster)
        assertEquals(dummyMovie.rate, movieData.rate)
        assertEquals(dummyMovie.realeaseDate, movieData.realeaseDate)

        viewModel.getMovieDetail(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail(){
        val tvShowDummy = MutableLiveData<DataModel>()
        tvShowDummy.value = dummyTvShow

        `when`(catalogRepository.getDetailTvShow(tvShowId)).thenReturn(tvShowDummy)

        val tvShowData = viewModel.getTvShowDetail(tvShowId).value as DataModel

        assertNotNull(tvShowData)
        assertEquals(dummyTvShow.id, tvShowData.id)
        assertEquals(dummyTvShow.name, tvShowData.name)
        assertEquals(dummyTvShow.desc, tvShowData.desc)
        assertEquals(dummyTvShow.poster, tvShowData.poster)
        assertEquals(dummyTvShow.rate, tvShowData.rate)
        assertEquals(dummyTvShow.realeaseDate, tvShowData.realeaseDate)

        viewModel.getTvShowDetail(tvShowId).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)

    }
}