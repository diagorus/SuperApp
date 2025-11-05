package com.lordnikius.nstretching.shared.stretching.ui.viewModel

import com.lordnikius.nstretching.shared.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.lordnikius.nstretching.shared.util.base.BaseUiState
import com.lordnikius.nstretching.shared.util.locale.StringUiData
import com.lordnikius.nstretching.shared.util.textToSpeech.TextToSpeechEngine

data class StretchingRoutineUiState(
    val currentLocale: SupportedLocaleWithTextToSpeechAvailability? = null,
    val supportedLocales: List<SupportedLocaleWithTextToSpeechAvailability> = emptyList(),
    val currentTextToSpeechEngine: TextToSpeechEngine? = null,
    val textToSpeechEngines: List<TextToSpeechEngine> = emptyList(),
    val state: StretchingRoutineState = StretchingRoutineState.IDLE,
    val exercise: StringUiData = StringUiData.Companion.Empty,
    val step: StringUiData = StringUiData.Companion.Empty,
    val showVoiceUnavailableDialog: ShowVoiceUnavailableDialog? = null,
    val showVoiceDownloadSettings: ShowVoiceDownloadSettings? = null,
) : BaseUiState