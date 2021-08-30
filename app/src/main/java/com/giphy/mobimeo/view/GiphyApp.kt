package com.giphy.mobimeo.view

import android.app.Application
import com.giphy.mobimeo.di.appModule
import com.giphy.mobimeo.di.repoModule
import com.giphy.mobimeo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GiphyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GiphyApp)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}