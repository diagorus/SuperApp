package com.lordnikius.superapp.util

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import com.lordnikius.superapp.util.module.coroutines.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Singleton
class TextToSpeechManager @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope applicationScope: CoroutineScope,
) {

//    init {
//        applicationScope.launch {
//            get()
//        }
//    }

    private val utteranceManager = UtteranceManager()
    private var ttsDeferred: Deferred<TextToSpeech> = initAsync()

    private suspend fun get(): TextToSpeech {
        Timber.d("get")
        try {
            return ttsDeferred.await()
        } catch (e: Throwable) {
            Timber.e(e)
            ttsDeferred = initAsync()
            return get()
        }
    }

    private fun initAsync(): Deferred<TextToSpeech> {
        Timber.d("init")
        return CompletableDeferred<TextToSpeech>().also { fut ->
            selfReference<TextToSpeech> {
                TextToSpeech(this@TextToSpeechManager.context) { status ->
                    Timber.d("init $status")
                    if (status == TextToSpeech.SUCCESS) {
                        //todo
                        val locale = Locale.US
                        val languageStatus = self.setLanguage(locale)
                        Timber.d("init $languageStatus")
                        when (languageStatus) {
                            TextToSpeech.LANG_AVAILABLE,
                            TextToSpeech.LANG_COUNTRY_AVAILABLE,
                            TextToSpeech.LANG_COUNTRY_VAR_AVAILABLE -> {
                                self.setOnUtteranceProgressListener(utteranceManager)
                                fut.complete(self)
                            }
                            else -> fut.completeExceptionally(Exception(languageStatus.toString()))
                        }
                    } else {
                        fut.completeExceptionally(Exception(status.toString()))
                    }
                }
            }
        }
    }

    suspend fun speak(value: String, queueMode: Int) {
        Timber.d("speak $value")
        val tts = get()
        suspendCoroutine { continuation ->
            val utterance = utteranceManager.create(value, queueMode, continuation)
            speak(tts, utterance)
        }
    }

    private fun speak(tts: TextToSpeech, utterance: Utterance) {
        Timber.d("speak " + utterance.id)
        tts.speak(utterance.text, utterance.queueMode, null, utterance.id)
    }
}

class UtteranceManager : UtteranceProgressListener() {
    private val utterances = HashMap<String, Utterance>()
    private var nextUtteranceId = 0L

    @Synchronized
    fun create(value: String, queueMode: Int, continuation: Continuation<Unit>): Utterance {
        Timber.d("create $value")
        val utteranceId = generateUtteranceId()
        val utterance = Utterance(utteranceId, value, queueMode, continuation)
        utterances[utteranceId] = utterance
        return utterance
    }

    @Synchronized
    override fun onStart(utteranceId: String) {
        Timber.d("onStart $utteranceId")
        val started = utterances.remove(utteranceId) as Utterance
        if (started.queueMode == TextToSpeech.QUEUE_FLUSH) {
            for (flushed in utterances.values) {
                flushed.continuation.resumeWithException(Exception("UTTERANCE_FLUSH"))
            }
            utterances.clear()
        }
        utterances[utteranceId] = started
    }

    @Synchronized
    override fun onDone(utteranceId: String) {
        Timber.d("onDone $utteranceId")
        val utterance = utterances.remove(utteranceId) as Utterance
        utterance.continuation.resume(Unit)
    }

    @Synchronized
    override fun onError(utteranceId: String) {
        Timber.d("onError $utteranceId")
        val utterance = utterances.remove(utteranceId) as Utterance
        utterance.continuation.resumeWithException(Exception("UTTERANCE_ERROR"))
    }

    private fun generateUtteranceId(): String {
        val utteranceId = nextUtteranceId++
        return utteranceId.toString()
    }
}

class Utterance(val id: String, val text: String, val queueMode: Int, val continuation: Continuation<Unit>)

// third-party solution
class SelfReference<T> internal constructor(initializer: SelfReference<T>.() -> T) {
    val self: T by lazy {
        inner ?: throw IllegalStateException("Do not use `self` until `initializer` finishes.")
    }

    private val inner = initializer()
}

fun <T> selfReference(initializer: SelfReference<T>.() -> T): T {
    return SelfReference(initializer).self
}