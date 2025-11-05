package com.lordnikius.nstretching.shared.util.textToSpeech

import com.lordnikius.nstretching.shared.util.locale.StringUiData
import com.lordnikius.nstretching.shared.util.locale.SupportedLocale

expect class TextToSpeechManager {
    suspend fun isLanguageAvailable(locale: SupportedLocale): Boolean
    suspend fun getEngines(): List<TextToSpeechEngine>
    suspend fun getCurrentEngine(): TextToSpeechEngine?
    suspend fun setEngine(engine: TextToSpeechEngine)
    suspend fun speak(text: StringUiData)
}