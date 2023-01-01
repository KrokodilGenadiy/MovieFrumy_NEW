package com.zaus_app.moviefrumy_new.di

import com.zaus_app.moviefrumy_new.di.modules.RemoteModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class
    ]
)

interface AppComponent {
}