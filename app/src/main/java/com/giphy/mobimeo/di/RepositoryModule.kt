package com.giphy.mobimeo.di

import com.giphy.mobimeo.data.repository.GiphyRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        GiphyRepository(get())
    }
}