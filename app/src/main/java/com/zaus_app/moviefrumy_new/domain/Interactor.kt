package com.zaus_app.moviefrumy_new.domain

import com.zaus_app.moviefrumy_new.data.API
import com.zaus_app.moviefrumy_new.data.TmdbApi
import com.zaus_app.moviefrumy_new.data.entity.TmdbResultsDto
import com.zaus_app.moviefrumy_new.utils.PreferenceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Response
import java.util.*

class Interactor(private val retrofitService: TmdbApi,  private val preferences: PreferenceProvider): BaseInteractor {
    override suspend fun getFilmsFromApi(page: Int): Response<TmdbResultsDto> {
        return retrofitService.getFilms(getDefaultCategoryFromPreferences(),API.KEY, Locale.getDefault().toLanguageTag(), page)
    }

    override suspend fun getFilmsByQuery(query: String, page: Int): Response<TmdbResultsDto> {
        return retrofitService.getFilmFromSearch(API.KEY, Locale.getDefault().toLanguageTag(), query, page)
    }

    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
}