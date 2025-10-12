package com.lordnikius.superapp.shared

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import com.lordnikius.superapp.shared.util.koin.KoinDebugApplication
import com.lordnikius.superapp.shared.util.textToSpeech.TextToSpeechManager
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.ksp.generated.*

class SuperApp : Application() {

    private val textToSpeechManager by inject<TextToSpeechManager>()

    override fun onCreate() {
        super.onCreate()

        KoinDebugApplication.startKoin {
            androidContext(this@SuperApp)
            androidLogger()
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