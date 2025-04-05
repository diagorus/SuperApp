package com.lordnikius.superapp

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            //todo start-up
            Timber.plant(Timber.DebugTree())
        }
    }
//
//    override fun attachBaseContext(base: Context) {
//        super.attachBaseContext(ContextCompat.getContextForLanguage(base))
//    }
}
