package com.zaus_app.moviefrumy_new.view.fragments.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.zaus_app.moviefrumy_new.App
import com.zaus_app.moviefrumy_new.data.entity.Film
import com.zaus_app.moviefrumy_new.data.paging.FilmPagingSourceImpl
import com.zaus_app.moviefrumy_new.domain.Interactor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    private val _query = MutableStateFlow("")
    private val query: StateFlow<String> = _query.asStateFlow()

    init {
        App.instance.dagger.inject(this)
    }

    fun getMovies(): Flow<PagingData<Film>> {
        return FilmPagingSourceImpl(query.value,interactor).getMovies()
            .map { pagingData ->
                pagingData.map {
                    it
                }
            }
            .cachedIn(viewModelScope)
    }

    fun setQuery(query: String) {
        _query.tryEmit(query)
    }
}