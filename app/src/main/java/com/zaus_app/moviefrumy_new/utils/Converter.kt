package com.zaus_app.moviefrumy_new.utils

import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.data.entity.TmdbFilm

object Converter {
    private const val NUMBER_OF_CHARS_TO_DROP = 2
    fun convertApiListToDtoList(list: List<TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(
                Film(
                    title = it.title,
                    poster = it.posterPath,
                    genres = convertGenreIdtoString(it.genreIds),
                    description = it.overview,
                    rating = it.voteAverage,
                    isInFavorites = false
                )
            )
        }
        return result
    }

    private fun convertGenreIdtoString(list: List<Int>): String {
        var result = "Жанры: "
        list.forEach {
            result = result + GenreList.genres.get(it)+", "
        }
        return result.dropLast(NUMBER_OF_CHARS_TO_DROP)
    }

}