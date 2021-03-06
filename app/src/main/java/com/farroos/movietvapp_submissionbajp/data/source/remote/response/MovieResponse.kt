package com.farroos.movietvapp_submissionbajp.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class MovieResponse(

	@field:SerializedName("id")
	var id: Int = 0,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,
)
