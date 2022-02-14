package com.farroos.movietvapp_submissionbajp.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tv_show_favorite")
@Parcelize
data class TvShowEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @NonNull
    @ColumnInfo(name = "tvShowId")
    val tvShowId: Int = 0,
    @NonNull
    @ColumnInfo(name = "tvShowName")
    val name: String? = null,
    @NonNull
    @ColumnInfo(name = "tvShowDesc")
    val desc: String? = null,
    @NonNull
    @ColumnInfo(name = "tvShowPoster")
    val poster: String? = null,
    @NonNull
    @ColumnInfo(name = "rate")
    val rate: Double? = null,
    @NonNull
    @ColumnInfo(name = "releaseDate")
    val realeaseDate: String? = null,
    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable