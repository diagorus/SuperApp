package com.lordnikius.nstretching.shared.util.textToSpeech

import android.speech.tts.TextToSpeech
import com.lordnikius.nstretching.shared.util.preferences.PreferencesDataStoreManager
import com.lordnikius.nstretching.shared.util.koin.CoroutinesModule.ApplicationScope
import com.lordnikius.nstretching.shared.util.locale.StringUiData
import com.lordnikius.nstretching.shared.util.locale.SupportedLocale
import com.lordnikius.nstretching.shared.util.locale.transformToString
import com.lordnikius.nstretching.shared.util.textToSpeech.auxiliaries.TextToSpeechInitializationManager
import com.lordnikius.nstretching.shared.util.textToSpeech.auxiliaries.UtteranceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single
import java.util.Locale
import kotlin.coroutines.suspendCoroutine

@Single
actual class TextToSpeechManager(
    @ApplicationScope
    private val applicationScope: CoroutineScope,
    private val textToSpeechInitializationManager: TextToSpeechInitializationManager,
    private val preferencesDataStoreManager: PreferencesDataStoreManager,
    private val utteranceManager: UtteranceManager,
) {

    actual suspend fun isLanguageAvailable(locale: SupportedLocale): Boolean {
        val textToSpeech = textToSpeechInitializationManager.getInstance()
        val languageStatus = textToSpeech.isLanguageAvailable(Locale(locale.languageTag))
        return languageStatus != TextToSpeech.LANG_MISSING_DATA &&
                languageStatus != TextToSpeech.LANG_NOT_SUPPORTED
    }

    actual suspend fun getCurrentEngine(): TextToSpeechEngine? {
        val textToSpeech = textToSpeechInitializationManager.getInstance()
        val currentEngine = preferencesDataStoreManager.chosenTextToSpeechEnginePackageFlow.firstOrNull()
            ?: textToSpeech.defaultEngine
        return getEngines().find { it.pkg == currentEngine }
    }

    actual suspend fun getEngines(): List<TextToSpeechEngine> {
        val textToSpeech = textToSpeechInitializationManager.getInstance()
        return textToSpeech.engines.map { TextToSpeechEngine(it.name, it.label) }
    }

    actual suspend fun setEngine(engine: TextToSpeechEngine) {
        preferencesDataStoreManager.saveChosenTextToSpeechEnginePackage(engine.pkg)
        textToSpeechInitializationManager.reinit()
    }

    actual suspend fun speak(text: StringUiData) {
        val tts = textToSpeechInitializationManager.getInstance()
        val localisedText = text.transformToString()
        suspendCoroutine { continuation ->
            val utterance = utteranceManager.create(localisedText, TextToSpeech.QUEUE_FLUSH, continuation)
            tts.speak(utterance.text, utterance.queueMode, null, utterance.id)
        }
    }

    fun onLanguageChanged() {
        applicationScope.launch {
            textToSpeechInitializationManager.reinit()
        }
    }
}