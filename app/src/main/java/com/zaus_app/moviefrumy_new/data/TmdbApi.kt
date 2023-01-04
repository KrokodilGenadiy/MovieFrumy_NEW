package com.zaus_app.moviefrumy_new.data

import com.zaus_app.moviefrumy_new.data.entity.TmdbResultsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("3/movie/popular")
    suspend fun getFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<TmdbResultsDto>

    @GET("3/search/movie")
    suspend fun getFilmFromSearch(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<TmdbResultsDto>
}