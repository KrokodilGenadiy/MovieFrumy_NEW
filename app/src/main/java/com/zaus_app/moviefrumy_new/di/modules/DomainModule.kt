package com.zaus_app.moviefrumy_new.di.modules

import android.content.Context
import com.zaus_app.moviefrumy_new.data.TmdbApi
import com.zaus_app.moviefrumy_new.data.paging.FilmPagingSource
import com.zaus_app.moviefrumy_new.data.paging.FilmPagingSourceImpl
import com.zaus_app.moviefrumy_new.domain.BaseInteractor
import com.zaus_app.moviefrumy_new.domain.Interactor
import com.zaus_app.moviefrumy_new.utils.PreferenceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule(val context: Context) {
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun providePreferences(context: Context) = PreferenceProvider(context)
    @Singleton
    @Provides
    fun provideInteractor(tmdbApi: TmdbApi,preferenceProvider: PreferenceProvider) = Interactor(tmdbApi,preferenceProvider)
}