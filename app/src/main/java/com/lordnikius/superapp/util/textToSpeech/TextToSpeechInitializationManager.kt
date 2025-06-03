package com.lordnikius.superapp.util.textToSpeech

import android.content.Context
import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.util.PreferencesDataStoreManager
import com.lordnikius.superapp.util.coroutines.ApplicationScope
import com.lordnikius.superapp.util.locale.LocaleManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Singleton
class TextToSpeechInitializationManager @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val localeManager: LocaleManager,
    private val preferencesDataStoreManager: PreferencesDataStoreManager,
    private val utteranceManager: UtteranceManager,
) {

    private var ttsDeferred: Deferred<TextToSpeech> = initAsync()

    private fun initAsync() = applicationScope.async {
        init()
    }

    private suspend fun init(): TextToSpeech {
        val engine = preferencesDataStoreManager.chosenTextToSpeechEnginePackageFlow.firstOrNull()
        return suspendCancellableCoroutine<TextToSpeech> { continuation ->
            SelfReference.create<TextToSpeech> {
                TextToSpeech(
                    applicationContext,
                    { status ->
                        if (status == TextToSpeech.SUCCESS) {
                            val currentLocale = localeManager.getCurrentLocale()
                            val languageStatus = self.setLanguage(Locale(currentLocale.languageTag))
                            when (languageStatus) {
                                TextToSpeech.LANG_AVAILABLE,
                                TextToSpeech.LANG_COUNTRY_AVAILABLE,
                                TextToSpeech.LANG_COUNTRY_VAR_AVAILABLE -> {
                                    self.setOnUtteranceProgressListener(utteranceManager)
                                }
                                else -> {
                                    Timber.e("Language not supported: $languageStatus")
                                }
                            }
                            continuation.resume(self)
                        } else {
                            continuation.resumeWithException(IllegalStateException("TextToSpeech initialization failed, code $status"))
                        }
                    },
                    engine
                )
            }
        }
    }

    suspend fun reinit() {
        val tts = ttsDeferred.await()
        tts.shutdown()
        ttsDeferred = initAsync()
    }

    suspend fun getInstance(): TextToSpeech {
        return ttsDeferred.await()
    }
}