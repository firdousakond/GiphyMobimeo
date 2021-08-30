package com.giphy.mobimeo.di

import com.giphy.mobimeo.viewmodel.GiphyViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        GiphyViewModel(get())
    }
}