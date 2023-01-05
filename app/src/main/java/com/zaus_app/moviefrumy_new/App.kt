package com.zaus_app.moviefrumy_new

import android.app.Application
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.zaus_app.moviefrumy_new.di.AppComponent
import com.zaus_app.moviefrumy_new.di.DaggerAppComponent
import com.zaus_app.moviefrumy_new.di.modules.DomainModule
import com.zaus_app.moviefrumy_new.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}