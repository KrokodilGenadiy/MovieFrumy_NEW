package com.zaus_app.moviefrumy_new.view.fragments.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.zaus_app.moviefrumy_new.App
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.data.paging.FilmPagingSourceImpl
import com.zaus_app.moviefrumy_new.domain.Interactor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    lateinit var filmPagingSource: FilmPagingSourceImpl

    init {
        App.instance.dagger.inject(this)
    }

    fun getMovies(): Flow<PagingData<Film>> {
        return filmPagingSource.getMovies()
            .map { pagingData ->
                pagingData.map {
                    it
                }
            }
            .cachedIn(viewModelScope)
    }
}