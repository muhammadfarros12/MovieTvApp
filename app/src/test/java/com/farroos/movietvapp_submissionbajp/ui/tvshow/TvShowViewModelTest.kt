package com.farroos.movietvapp_submissionbajp.ui.tvshow

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest{
private lateinit var viewModel: TvShowViewModel

@Before
fun setUp(){
    viewModel = TvShowViewModel()
}

    @Test
    fun getTvShow(){
val tvShowEntity = viewModel.getTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(10, tvShowEntity.size)
    }
}