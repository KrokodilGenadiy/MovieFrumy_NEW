package com.zaus_app.moviefrumy_new.data.entity

data class Film(
    val title: String,
    val poster: Int,
    val description: String,
    var rating: Double = 0.0,
    var isInFavorites: Boolean = false
)