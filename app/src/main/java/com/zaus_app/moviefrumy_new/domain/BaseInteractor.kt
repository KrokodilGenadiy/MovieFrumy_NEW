package com.zaus_app.moviefrumy_new.domain

import com.zaus_app.moviefrumy_new.data.entity.TmdbResultsDto
import retrofit2.Response

interface BaseInteractor {
    suspend fun getFilmsFromApi(page: Int): Response<TmdbResultsDto>
}