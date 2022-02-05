package com.farroos.movietvapp_submissionbajp.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.farroos.movietvapp_submissionbajp.LiveDataTestUtil
import com.farroos.movietvapp_submissionbajp.data.source.remote.RemoteDataSource
import com.farroos.movietvapp_submissionbajp.utility.datadummy.DataDummyMovie
import com.farroos.movietvapp_submissionbajp.utility.datadummy.DataDummyTvShow
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CatalogRepositoryTest {
    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote)

    private val listMovieResponse = DataDummyMovie.generateDummyMovie()
    private val movieId = listMovieResponse[0].id
    private val listTvShowResponse = DataDummyTvShow.generateTvShow()
    private val tvShowId = listTvShowResponse[0].id
    private val movieResponse = DataDummyMovie.generateDummyMovie()[0]
    private val tvShowResponse = DataDummyTvShow.generateTvShow()[0]


    @Test
    fun getMovies() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as RemoteDataSource.LoadMovieCallback).onAllMovieReceived(
                    listMovieResponse
                )
                null
            }.`when`(remote).getMovies(any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getMovies())

        runBlocking {
            verify(remote).getMovies(any())
        }

        assertNotNull(data)
        assertEquals(listMovieResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
                doAnswer {
                    (it.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(
                        movieResponse
                    )
                    null
                }.`when`(remote).getMovieDetail(eq(movieId), any())


            val data = LiveDataTestUtil.getValue(catalogRepository.getDetailMovie(movieId))

            runBlocking {
                verify(remote).getMovieDetail(eq(movieId), any())
            }

            assertNotNull(data)
            assertEquals(movieResponse.id, data.id)
        }
    }

    @Test
    fun getTvShow() {
        runBlocking {
            doAnswer {
                (it.arguments[0] as RemoteDataSource.LoadTvShowCallback).onAllTvShowReceived(
                    listTvShowResponse
                )
                null
            }.`when`(remote).getTvShow(any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getTvShow())

        runBlocking {
            verify(remote).getTvShow(any())
        }

        assertNotNull(data)
        assertEquals(listTvShowResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getTvShowDetail() {
        runBlocking {
                doAnswer {
                    (it.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(
                        tvShowResponse
                    )
                    null
                }.`when`(remote).getTvShowDetail(eq(tvShowId), any())

            val data = LiveDataTestUtil.getValue(catalogRepository.getDetailTvShow(tvShowId))

            runBlocking {
                verify(remote).getTvShowDetail(eq(tvShowId), any())
            }

            assertNotNull(data)
            assertEquals(tvShowResponse.id, data.id)
        }
    }

}