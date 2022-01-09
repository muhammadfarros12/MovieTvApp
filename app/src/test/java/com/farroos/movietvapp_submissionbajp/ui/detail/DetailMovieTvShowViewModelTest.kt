package com.farroos.movietvapp_submissionbajp.ui.detail

import com.farroos.movietvapp_submissionbajp.utility.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailMovieTvShowViewModelTest {
    private lateinit var viewModel: DetailMovieTvShowViewModel

    private val tvShowDummy = DataDummy.generateDummyTvShow()[0]
    private val id = tvShowDummy.id

    @Before
    fun setUp() {
        viewModel = DetailMovieTvShowViewModel()
        viewModel.setSelectedMovieTvShow(id)
    }

    @Test
    fun getMovieTvShow() {
        viewModel.setSelectedMovieTvShow(tvShowDummy.id)
        val tvShowEntity = viewModel.getDetailMovieTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(tvShowDummy.id, tvShowEntity.id)
        assertEquals(tvShowDummy.title, tvShowEntity.title)
        assertEquals(tvShowDummy.year, tvShowEntity.year)
        assertEquals(tvShowDummy.imagePath, tvShowEntity.imagePath)
        assertEquals(tvShowDummy.actor, tvShowEntity.actor)
        assertEquals(tvShowDummy.director, tvShowEntity.director)
        assertEquals(tvShowDummy.description, tvShowEntity.description)
        assertEquals(tvShowDummy.duration, tvShowEntity.duration)
        assertEquals(tvShowDummy.genre, tvShowEntity.genre)
    }
}