package com.farroos.movietvapp_submissionbajp.data.source.remote.api

import com.farroos.movietvapp_submissionbajp.BuildConfig
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.ListResponse
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.MovieResponse
import com.farroos.movietvapp_submissionbajp.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Home
    @GET("movie/now_playing")
    fun getMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<ListResponse<MovieResponse>>

    @GET("tv/on_the_air")
    fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<ListResponse<TvShowResponse>>

    // Detail
    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TvShowResponse>

}