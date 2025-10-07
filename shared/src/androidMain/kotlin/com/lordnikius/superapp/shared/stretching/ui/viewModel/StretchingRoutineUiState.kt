package com.lordnikius.superapp.shared.stretching.ui.viewModel

import com.lordnikius.superapp.shared.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.lordnikius.superapp.shared.util.textToSpeech.TextToSpeechEngine
import com.lordnikius.superapp.shared.util.base.BaseUiState
import com.lordnikius.superapp.shared.util.locale.StringUiData

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