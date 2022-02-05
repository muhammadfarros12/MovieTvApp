package com.farroos.movietvapp_submissionbajp.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.farroos.movietvapp_submissionbajp.data.source.CatalogRepository
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel
import com.farroos.movietvapp_submissionbajp.utility.datadummy.DataDummyModelMovie
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private val dummyMovie = DataDummyModelMovie.generateDummyModelMovie()

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<DataModel>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun getListMovies(){
        val movie = MutableLiveData<List<DataModel>>()
        movie.value = dummyMovie

        `when`(catalogRepository.getMovies()).thenReturn(movie)

        val dataListMovie = viewModel.getMovie().value

        verify(catalogRepository).getMovies()
        assertNotNull(dataListMovie)
        assertEquals(2, dataListMovie?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}