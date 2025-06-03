package com.lordnikius.superapp.stretching.ui

import com.lordnikius.superapp.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.lordnikius.superapp.util.locale.SupportedLocale
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechEngine

class StretchingRoutineCallbacks(
    val onStartPauseClick: () -> Unit,
    val onStopClick: () -> Unit,
    val onLanguageClick: (SupportedLocaleWithTextToSpeechAvailability) -> Unit,
    val onEngineClick: (TextToSpeechEngine) -> Unit,
)