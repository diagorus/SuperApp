package com.lordnikius.nstretching.shared.stretching.ui

import com.lordnikius.nstretching.shared.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.lordnikius.nstretching.shared.util.textToSpeech.TextToSpeechEngine

class StretchingRoutineCallbacks(
    val onStartPauseClick: () -> Unit,
    val onStopClick: () -> Unit,
    val onLanguageClick: (SupportedLocaleWithTextToSpeechAvailability) -> Unit,
    val onEngineClick: (TextToSpeechEngine) -> Unit,
)