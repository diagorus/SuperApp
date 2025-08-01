package com.lordnikius.superapp.stretching.viewModel

import com.lordnikius.superapp.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.lordnikius.superapp.util.base.BaseUiState
import com.lordnikius.superapp.data.util.locale.StringUiData
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechEngine

data class StretchingRoutineUiState(
    val currentLocale: SupportedLocaleWithTextToSpeechAvailability? = null,
    val supportedLocales: List<SupportedLocaleWithTextToSpeechAvailability> = emptyList(),
    val currentTextToSpeechEngine: TextToSpeechEngine? = null,
    val textToSpeechEngines: List<TextToSpeechEngine> = emptyList(),
    val state: StretchingRoutineState = StretchingRoutineState.IDLE,
    val exercise: StringUiData = StringUiData.Empty,
    val step: StringUiData = StringUiData.Empty,
    val showVoiceUnavailableDialog: ShowVoiceUnavailableDialog? = null,
    val showVoiceDownloadSettings: ShowVoiceDownloadSettings? = null,
) : BaseUiState