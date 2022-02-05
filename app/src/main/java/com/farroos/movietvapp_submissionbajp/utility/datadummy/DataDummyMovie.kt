package com.farroos.movietvapp_submissionbajp.utility.datadummy

import com.farroos.movietvapp_submissionbajp.data.source.remote.response.MovieResponse

object DataDummyMovie {

    fun generateDummyMovie(): List<MovieResponse> {
        val movie = ArrayList<MovieResponse>()
        movie.add(
            MovieResponse(
                1,
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                8.4,
                "2021-12-15",
            )
        )

        movie.add(
            MovieResponse(
                2,
                "Eternals",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "/bcCBq9N1EMo3daNIjWJ8kYvrQm6.jpg",
                7.2,
                "2021-11-03",
            )
        )

        return movie
    }

}