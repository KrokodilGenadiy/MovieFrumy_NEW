package com.zaus_app.moviefrumy_new.domain

import com.zaus_app.moviefrumy_new.data.API
import com.zaus_app.moviefrumy_new.data.TmdbApi
import com.zaus_app.moviefrumy_new.data.entity.TmdbResultsDto
import retrofit2.Response
import java.util.*

class Interactor(private val retrofitService: TmdbApi): BaseInteractor {
    override suspend fun getFilmsFromApi(page: Int): Response<TmdbResultsDto> {
        return retrofitService.getFilms(API.KEY, Locale.getDefault().toLanguageTag(), page)
    }

    override suspend fun getFilmsByQuery(query: String, page: Int): Response<TmdbResultsDto> {
        return retrofitService.getFilmFromSearch(API.KEY, Locale.getDefault().toLanguageTag(),query, page)
    }
}