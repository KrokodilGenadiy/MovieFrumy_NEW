package com.zaus_app.moviefrumy_new.domain

import com.zaus_app.moviefrumy_new.data.API
import com.zaus_app.moviefrumy_new.data.TmdbApi
import com.zaus_app.moviefrumy_new.data.entity.TmdbResultsDto
import retrofit2.Response
import java.util.*

class Interactor(private val retrofitService: TmdbApi) {
    suspend fun getFilmsFromApi(page: Int): Response<TmdbResultsDto> {
        return retrofitService.getFilms(API.KEY, Locale.getDefault().toLanguageTag(), page)
    }
}