package com.farroos.movietvapp_submissionbajp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import androidx.lifecycle.Observer
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
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
    private val movieId = dummyMovie.movieId
    private val dummyTvShow = DataDummyModelTvShow.generateModelTvShow()[0]
    private val tvShowId = dummyTvShow.tvShowId

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        `when`(catalogRepository.getDetailMovie(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId).value as MovieEntity

        assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData.id)
        assertEquals(dummyMovie.movieId, movieData.movieId)
        assertEquals(dummyMovie.name, movieData.name)
        assertEquals(dummyMovie.desc, movieData.desc)
        assertEquals(dummyMovie.poster, movieData.poster)
        assertEquals(dummyMovie.rate, movieData.rate)
        assertEquals(dummyMovie.realeaseDate, movieData.realeaseDate)

        viewModel.getMovieDetail(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail(){
        val tvShowDummy = MutableLiveData<TvShowEntity>()
        tvShowDummy.value = dummyTvShow

        `when`(catalogRepository.getDetailTvShow(tvShowId)).thenReturn(tvShowDummy)

        val tvShowData = viewModel.getTvShowDetail(tvShowId).value as TvShowEntity

        assertNotNull(tvShowData)
        assertEquals(dummyTvShow.id, tvShowData.id)
        assertEquals(dummyTvShow.tvShowId, tvShowData.tvShowId)
        assertEquals(dummyTvShow.name, tvShowData.name)
        assertEquals(dummyTvShow.desc, tvShowData.desc)
        assertEquals(dummyTvShow.poster, tvShowData.poster)
        assertEquals(dummyTvShow.rate, tvShowData.rate)
        assertEquals(dummyTvShow.realeaseDate, tvShowData.realeaseDate)

        viewModel.getTvShowDetail(tvShowId).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)

    }
}