package com.lordnikius.superapp.util.textToSpeech

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.lordnikius.superapp.util.StringUiData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class TextToSpeechManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val utteranceManager = UtteranceManager()
    private var ttsDeferred: Deferred<TextToSpeech> = initAsync()

    private suspend fun get(): TextToSpeech {
        try {
            return ttsDeferred.await()
        } catch (e: Throwable) {
            Timber.e(e)
            ttsDeferred = initAsync()
            return get()
        }
    }

    private fun initAsync(): Deferred<TextToSpeech> {
        return CompletableDeferred<TextToSpeech>().also { deferred ->
            SelfReference.create<TextToSpeech> {
                TextToSpeech(context) { status ->
                    if (status == TextToSpeech.SUCCESS) {
                        val primaryLocale = AppCompatDelegate.getApplicationLocales()[0]
                        val languageStatus = self.setLanguage(primaryLocale)
                        when (languageStatus) {
                            TextToSpeech.LANG_AVAILABLE,
                            TextToSpeech.LANG_COUNTRY_AVAILABLE,
                            TextToSpeech.LANG_COUNTRY_VAR_AVAILABLE -> {
                                self.setOnUtteranceProgressListener(utteranceManager)
                                deferred.complete(self)
                            }
                            else -> {
                                deferred.completeExceptionally(Exception(languageStatus.toString()))
                            }
                        }
                    } else {
                        deferred.completeExceptionally(Exception(status.toString()))
                    }
                }
            }
        }
    }

    suspend fun speak(text: StringUiData) {
        val tts = get()
        suspendCoroutine { continuation ->
            val localisedText = text.transformToString(context)
            val utterance = utteranceManager.create(localisedText, TextToSpeech.QUEUE_FLUSH, continuation)
            tts.speak(utterance.text, utterance.queueMode, null, utterance.id)
        }
    }
}
