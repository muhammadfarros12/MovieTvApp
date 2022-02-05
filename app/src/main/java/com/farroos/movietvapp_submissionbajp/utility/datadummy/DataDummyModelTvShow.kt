package com.farroos.movietvapp_submissionbajp.utility.datadummy

import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel

object DataDummyModelTvShow {

    fun generateModelTvShow(): List<DataModel> {
        val tvShow = ArrayList<DataModel>()

        tvShow.add(
            DataModel(
                1,
                "Mayday",
                "Revealing the dark truth that aviation safety improves one crash at a time, Mayday  investigates legendary aviation disasters to find out what went wrong and why.\\n\\nBased on cockpit voice recorders, accident reports and eyewitness accounts, every episode also features interviews, state-of-the-art CGI and gripping reenactments.",
                "/dTTl7DR23zr8IlPSMh2Vcfumyo3.jpg",
                8.2,
                "08-09-2020"
            )
        )
        tvShow.add(
            DataModel(
                2,
                "Top Gear",
                "This fast-paced and stunt-filled motor show tests whether cars, both mundane and extraordinary, live up to their manufacturers' claims. The long-running show travels to locations around the world, performing extreme stunts and challenges to see what the featured cars are capable of doing. The current hosts are Paddy Mcguinness, Chris Harris and Andrew \\\"Freddie\\\" Flintoff.",
                "/aqM6QnuhSXzjHlKbXyKUqxaGiWu.jpg",
                7.3,
                "13-07-2021"
            )
        )

        return tvShow

    }

}