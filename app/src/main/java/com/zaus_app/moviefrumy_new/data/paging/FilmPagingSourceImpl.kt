package com.zaus_app.moviefrumy_new.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.domain.BaseInteractor
import com.zaus_app.moviefrumy_new.domain.Interactor
import kotlinx.coroutines.flow.Flow

class FilmPagingSourceImpl(
    private val query: String,
    private val interactor: BaseInteractor) {
    fun getMovies(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FilmPagingSource(query,interactor)
            }
        ).flow
    }
    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}