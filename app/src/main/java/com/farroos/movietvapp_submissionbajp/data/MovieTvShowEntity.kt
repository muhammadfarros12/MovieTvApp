package com.farroos.movietvapp_submissionbajp.data

data class MovieTvShowEntity(
    val id: String,
    var imagePath: Int,
    var title: String,
    var description: String,
    var genre: String,
    var year: String,
    var actor: String,
    var director: String,
    var duration: String,
    var country: String,
    var eps: Int
)