package com.lordnikius.superapp

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.speech.tts.TextToSpeech
import androidx.core.content.ContextCompat
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    internal lateinit var textToSpeechManager: TextToSpeechManager

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            //todo start-up
            Timber.plant(Timber.DebugTree())
        }

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                if (intent.action == Intent.ACTION_LOCALE_CHANGED) {
                    textToSpeechManager.onLanguageChanged()
                }
            }
        }
        registerReceiver(receiver, IntentFilter(Intent.ACTION_LOCALE_CHANGED))

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(ContextCompat.getContextForLanguage(base))
    }
}