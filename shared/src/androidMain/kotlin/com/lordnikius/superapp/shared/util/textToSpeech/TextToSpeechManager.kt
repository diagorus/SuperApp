package com.lordnikius.superapp.shared.util.textToSpeech

import android.content.Context
import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.shared.util.koin.CoroutinesModule
import com.lordnikius.superapp.shared.util.koin.CoroutinesModule.ApplicationScope
import com.lordnikius.superapp.shared.util.locale.transformToString
import com.lordnikius.superapp.shared.util.locale.StringUiData
import com.lordnikius.superapp.shared.util.locale.SupportedLocale
import com.lordnikius.superapp.shared.util.PreferencesDataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single
import java.util.*
import kotlin.coroutines.suspendCoroutine

@Single
class TextToSpeechManager(
    @ApplicationScope
    private val applicationScope: CoroutineScope,
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
        val localisedText = text.transformToString()
        suspendCoroutine { continuation ->
            val utterance = utteranceManager.create(localisedText, TextToSpeech.QUEUE_FLUSH, continuation)
            tts.speak(utterance.text, utterance.queueMode, null, utterance.id)
        }
    }
}
