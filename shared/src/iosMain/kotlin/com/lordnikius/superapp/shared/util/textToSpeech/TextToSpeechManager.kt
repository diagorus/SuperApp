package com.lordnikius.superapp.shared.util.textToSpeech

import com.lordnikius.superapp.shared.util.locale.StringUiData
import com.lordnikius.superapp.shared.util.locale.SupportedLocale
import com.lordnikius.superapp.shared.util.locale.transformToString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single
import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance

actual class TextToSpeechManager(
    val mainDispatcher: CoroutineDispatcher,
) {
    actual suspend fun isLanguageAvailable(locale: SupportedLocale): Boolean {
        return true
    }

    actual suspend fun getEngines(): List<TextToSpeechEngine> {
        return emptyList()
    }

    actual suspend fun getCurrentEngine(): TextToSpeechEngine? {
        return null
    }

    actual suspend fun setEngine(engine: TextToSpeechEngine) {
        // do nothing
    }

    actual suspend fun speak(text: StringUiData) {
        withContext(mainDispatcher) {
            val localisedText = text.transformToString()
            val utterance = AVSpeechUtterance(string = localisedText)
            val synth = AVSpeechSynthesizer()
            synth.speakUtterance(utterance)
        }
    }
}