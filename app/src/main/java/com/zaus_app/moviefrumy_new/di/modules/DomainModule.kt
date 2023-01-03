package com.zaus_app.moviefrumy_new.di.modules

import com.zaus_app.moviefrumy_new.data.TmdbApi
import com.zaus_app.moviefrumy_new.data.paging.FilmPagingSource
import com.zaus_app.moviefrumy_new.data.paging.FilmPagingSourceImpl
import com.zaus_app.moviefrumy_new.domain.BaseInteractor
import com.zaus_app.moviefrumy_new.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(tmdbApi: TmdbApi) = Interactor(tmdbApi)

    @Singleton
    @Provides
    fun provideDataSourceImplementation(interactor: Interactor) = FilmPagingSourceImpl(interactor)
}