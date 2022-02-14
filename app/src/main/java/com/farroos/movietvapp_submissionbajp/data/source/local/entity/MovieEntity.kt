package com.farroos.movietvapp_submissionbajp.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_favorite")
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: Int = 0,
    @NonNull
    @ColumnInfo(name = "movieName")
    val name: String? = null,
    @NonNull
    @ColumnInfo(name = "movieDesc")
    val desc: String? = null,
    @NonNull
    @ColumnInfo(name = "moviePoster")
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