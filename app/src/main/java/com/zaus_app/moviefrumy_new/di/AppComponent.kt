package com.zaus_app.moviefrumy_new.di

import com.zaus_app.moviefrumy_new.di.modules.DomainModule
import com.zaus_app.moviefrumy_new.di.modules.RemoteModule
import com.zaus_app.moviefrumy_new.view.fragments.filters_fragment.FiltersFragmentViewModel
import com.zaus_app.moviefrumy_new.view.fragments.home_fragment.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class,
        RemoteModule::class
    ]
)

interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(filtersFragmentViewModel: FiltersFragmentViewModel)
}