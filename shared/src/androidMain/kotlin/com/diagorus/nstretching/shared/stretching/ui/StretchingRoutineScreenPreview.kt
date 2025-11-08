package com.diagorus.nstretching.shared.stretching.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.diagorus.nstretching.shared.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.diagorus.nstretching.shared.stretching.ui.viewModel.StretchingRoutineUiState
import com.diagorus.nstretching.shared.util.locale.StringUiData
import com.diagorus.nstretching.shared.util.locale.SupportedLocale

@Preview
@Composable
private fun StretchingRoutineScreenPreview0() {
    MaterialTheme {
        StretchingRoutineScreen(
            StretchingRoutineUiState(
                supportedLocales = listOf(
                    SupportedLocaleWithTextToSpeechAvailability(
                        supportedLocale = SupportedLocale("en", "English"),
                        isTextToSpeechAvailable = true,
                    ),
                    SupportedLocaleWithTextToSpeechAvailability(
                        supportedLocale = SupportedLocale("pl", "Polski"),
                        isTextToSpeechAvailable = false,
                    ),
                ),
                currentLocale = SupportedLocaleWithTextToSpeechAvailability(
                    supportedLocale = SupportedLocale("en", "English"),
                    isTextToSpeechAvailable = true,
                ),
                exercise = StringUiData.Value("Hands"),
                step = StringUiData.Value("Preparation"),
            ),
            StretchingRoutineCallbacks(
                onStartPauseClick = {},
                onStopClick = {},
                onLanguageClick = {},
                onEngineClick = {},
            )
        )
    }
}