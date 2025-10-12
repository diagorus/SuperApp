package com.lordnikius.superapp.shared.util.textToSpeech

import com.lordnikius.superapp.shared.util.locale.StringUiData
import com.lordnikius.superapp.shared.util.locale.SupportedLocale
import org.koin.core.annotation.Single

@Single
actual class TextToSpeechManager {
    actual suspend fun isLanguageAvailable(locale: SupportedLocale): Boolean {
        return false
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
        // do nothing
    }
}