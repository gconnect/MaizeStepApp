package com.chuks.maizestemapp

import android.app.Application
import com.chuks.maizestemapp.common.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.wikiedufoundation.wikiedudashboard.di.databaseModule
import org.wikiedufoundation.wikiedudashboard.di.repositoryModule
import org.wikiedufoundation.wikiedudashboard.di.viewModelModule
import timber.log.Timber


/**
 * The App class extends Application and will be called or declared in the manifest
 * Koin modules are registered here
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@App)
            modules(listOf(apiModule, databaseModule, repositoryModule, viewModelModule))
        }

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}