package com.lordnikius.superapp.util.textToSpeech

import android.content.Context
import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.util.PreferencesDataStoreManager
import com.lordnikius.superapp.util.coroutines.ApplicationScope
import com.lordnikius.superapp.util.locale.StringUiData
import com.lordnikius.superapp.util.locale.SupportedLocale
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.suspendCoroutine

@Singleton
class TextToSpeechManager @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val textToSpeechInitializationManager: TextToSpeechInitializationManager,
    private val preferencesDataStoreManager: PreferencesDataStoreManager,
    private val utteranceManager: UtteranceManager,
) {

    suspend fun isLanguageAvailable(supportedLocale: SupportedLocale): Boolean {
        val textToSpeech = textToSpeechInitializationManager.getInstance()
        val languageStatus = textToSpeech.isLanguageAvailable(Locale(supportedLocale.languageTag))
        return languageStatus != TextToSpeech.LANG_MISSING_DATA &&
                languageStatus != TextToSpeech.LANG_NOT_SUPPORTED
    }

    suspend fun getCurrentEngine(): TextToSpeechEngine? {
        val textToSpeech = textToSpeechInitializationManager.getInstance()
        val currentEngine = preferencesDataStoreManager.chosenTextToSpeechEnginePackageFlow.firstOrNull()
            ?: textToSpeech.defaultEngine
        return getEngines().find { it.pkg == currentEngine }
    }

    suspend fun getEngines(): List<TextToSpeechEngine> {
        val textToSpeech = textToSpeechInitializationManager.getInstance()
        return textToSpeech.engines.map { TextToSpeechEngine(it.name, it.label) }
    }

    suspend fun setEngine(engine: TextToSpeechEngine) {
        preferencesDataStoreManager.saveChosenTextToSpeechEnginePackage(engine.pkg)
        textToSpeechInitializationManager.reinit()
    }

    fun onLanguageChanged() {
        applicationScope.launch {
            textToSpeechInitializationManager.reinit()
        }
    }

    suspend fun speak(text: StringUiData) {
        val tts = textToSpeechInitializationManager.getInstance()
        suspendCoroutine { continuation ->
            val localisedText = text.transformToString(applicationContext)
            val utterance = utteranceManager.create(localisedText, TextToSpeech.QUEUE_FLUSH, continuation)
            tts.speak(utterance.text, utterance.queueMode, null, utterance.id)
        }
    }
}
