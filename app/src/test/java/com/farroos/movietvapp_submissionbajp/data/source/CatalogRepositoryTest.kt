package com.farroos.movietvapp_submissionbajp.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.farroos.movietvapp_submissionbajp.LiveDataTestUtil
import com.farroos.movietvapp_submissionbajp.PagedListUtil
import com.farroos.movietvapp_submissionbajp.data.source.local.LocalDataSource
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.MovieEntity
import com.farroos.movietvapp_submissionbajp.data.source.local.entity.TvShowEntity
import com.farroos.movietvapp_submissionbajp.data.source.remote.RemoteDataSource
import com.farroos.movietvapp_submissionbajp.utility.datadummy.DataDummyModelMovie
import com.farroos.movietvapp_submissionbajp.utility.datadummy.DataDummyModelTvShow
import com.farroos.movietvapp_submissionbajp.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogRepositoryTest {
    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote, local)

    private val listMovie = DataDummyModelMovie.generateDummyModelMovie()
    private val listTvShow = DataDummyModelTvShow.generateModelTvShow()
    private val movie = DataDummyModelMovie.generateDummyModelMovie()[0]
    private val tvShow = DataDummyModelTvShow.generateModelTvShow()[0]


    @Test
    fun getMovies() {
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovie()).thenReturn(dataSource)
        catalogRepository.getMovies()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummyModelMovie.generateDummyModelMovie()))
        verify(local).getMovie()
        assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTvShows() {
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShow()).thenReturn(dataSource)
        catalogRepository.getTvShow()

        val tvShowEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummyModelTvShow.generateModelTvShow()))
        verify(local).getTvShow()
        assertNotNull(tvShowEntity.data)
        assertEquals(listTvShow.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movie
        `when`(local.getDetailMovie(movie.movieId)).thenReturn(dummyMovie)

        val data = LiveDataTestUtil.getValue(catalogRepository.getDetailMovie(movie.movieId))
        verify(local).getDetailMovie(movie.movieId)
        assertNotNull(data)
        assertEquals(movie.movieId, data.movieId)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = tvShow
        `when`(local.getDetailTvShow(tvShow.tvShowId)).thenReturn(dummyTvShow)

        val data = LiveDataTestUtil.getValue(catalogRepository.getDetailTvShow(tvShow.tvShowId))
        verify(local).getDetailTvShow(tvShow.tvShowId)
        assertNotNull(data)
        assertEquals(tvShow.tvShowId, data.tvShowId)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSource)
        catalogRepository.getFavoriteMovie()

        val movieEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummyModelMovie.generateDummyModelMovie()))
        verify(local).getFavoriteMovie()
        assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSource)
        catalogRepository.getFavoriteTvShow()

        val tvShowEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummyModelTvShow.generateModelTvShow()))
        verify(local).getFavoriteTvShow()
        assertNotNull(tvShowEntity.data)
        assertEquals(listMovie.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

}