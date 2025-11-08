package com.diagorus.nstretching.shared.stretching.ui

import com.diagorus.nstretching.shared.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.diagorus.nstretching.shared.util.textToSpeech.TextToSpeechEngine

class StretchingRoutineCallbacks(
    val onStartPauseClick: () -> Unit,
    val onStopClick: () -> Unit,
    val onLanguageClick: (SupportedLocaleWithTextToSpeechAvailability) -> Unit,
    val onEngineClick: (TextToSpeechEngine) -> Unit,
)