package com.zaus_app.moviefrumy_new.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val title: String,
    val poster: String,
    val genres: String,
    val description: String,
    var rating: Double = 0.0,
    var isInFavorites: Boolean = false
): Parcelable