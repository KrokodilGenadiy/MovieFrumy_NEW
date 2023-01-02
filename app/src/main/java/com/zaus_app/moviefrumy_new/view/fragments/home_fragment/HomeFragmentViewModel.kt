package com.zaus_app.moviefrumy_new.view.fragments.home_fragment

import androidx.lifecycle.ViewModel
import com.zaus_app.moviefrumy_new.App
import com.zaus_app.moviefrumy_new.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }
}