package com.lordnikius.superapp.shared.stretching.ui

import com.lordnikius.superapp.shared.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.lordnikius.superapp.shared.util.textToSpeech.TextToSpeechEngine

class StretchingRoutineCallbacks(
    val onStartPauseClick: () -> Unit,
    val onStopClick: () -> Unit,
    val onLanguageClick: (SupportedLocaleWithTextToSpeechAvailability) -> Unit,
    val onEngineClick: (TextToSpeechEngine) -> Unit,
)