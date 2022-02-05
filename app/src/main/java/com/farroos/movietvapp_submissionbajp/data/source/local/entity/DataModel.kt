package com.farroos.movietvapp_submissionbajp.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    var id: Int = 0,
    var name: String? = null,
    var desc: String? = null,
    var poster: String? = null,
    var rate: Double? = null,
    var realeaseDate: String? = null
) : Parcelable