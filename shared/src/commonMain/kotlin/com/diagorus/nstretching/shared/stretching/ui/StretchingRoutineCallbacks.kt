package com.diagorus.nstretching.shared.stretching.ui

import com.diagorus.nstretching.shared.stretching.LocaleWithTextToSpeechAvailability
import com.diagorus.nstretching.shared.util.textToSpeech.TextToSpeechEngine

class StretchingRoutineCallbacks(
    val onStartPauseClick: () -> Unit,
    val onStopClick: () -> Unit,
    val onLanguageClick: (LocaleWithTextToSpeechAvailability) -> Unit,
    val onEngineClick: (TextToSpeechEngine) -> Unit,
)