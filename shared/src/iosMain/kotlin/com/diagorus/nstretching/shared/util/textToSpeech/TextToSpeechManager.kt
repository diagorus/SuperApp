package com.diagorus.nstretching.shared.util.textToSpeech

import com.diagorus.nstretching.shared.util.locale.LocaleManager
import com.diagorus.nstretching.shared.util.locale.StringUiData
import com.diagorus.nstretching.shared.util.locale.LocaleWithName
import com.diagorus.nstretching.shared.util.locale.transformToString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import platform.AVFAudio.AVSpeechSynthesisVoice
import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance

actual class TextToSpeechManager(
    val mainDispatcher: CoroutineDispatcher,
    val localeManager: LocaleManager,
) {
    actual suspend fun isLanguageAvailable(locale: LocaleWithName): Boolean {

//        val voices = AVSpeechSynthesisVoice.speechVoices()
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
            val currentLocaleTag = localeManager.getCurrentLocale().supportedLocale.tag
            val utterance = AVSpeechUtterance(string = localisedText).apply {
                voice = AVSpeechSynthesisVoice.voiceWithLanguage(currentLocaleTag)
            }
            val synth = AVSpeechSynthesizer()
            synth.speakUtterance(utterance)
        }
    }
}