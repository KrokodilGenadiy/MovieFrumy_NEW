package com.zaus_app.moviefrumy_new.view.fragments.filters_fragment

import androidx.lifecycle.ViewModel
import com.zaus_app.moviefrumy_new.App
import com.zaus_app.moviefrumy_new.domain.Interactor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FiltersFragmentViewModel: ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    private var _currentCategory = MutableStateFlow("")
    val currentCategory: StateFlow<String> = _currentCategory.asStateFlow()

    init {
        App.instance.dagger.inject(this)
        _currentCategory.value = interactor.getDefaultCategoryFromPreferences()
    }
}