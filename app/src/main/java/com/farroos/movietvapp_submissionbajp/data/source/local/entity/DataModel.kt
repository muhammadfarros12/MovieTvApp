package com.farroos.movietvapp_submissionbajp.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    val id: Int = 0,
    val name: String? = null,
    val desc: String? = null,
    val poster: String? = null,
    val rate: Double? = null,
    val realeaseDate: String? = null
) : Parcelable