package com.lordnikius.superapp.shared.stretching.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lordnikius.superapp.shared.App
import com.lordnikius.superapp.shared.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.lordnikius.superapp.shared.stretching.ui.viewModel.StretchingRoutineState
import com.lordnikius.superapp.shared.stretching.ui.viewModel.StretchingRoutineUiState
import com.lordnikius.superapp.shared.stretching.ui.viewModel.StretchingRoutineViewModel
import com.lordnikius.superapp.shared.util.locale.StringUiData
import com.lordnikius.superapp.shared.util.locale.SupportedLocale
import com.lordnikius.superapp.shared.util.locale.transformToStringCompose
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.ic_stop
import superapp.shared.generated.resources.pause
import superapp.shared.generated.resources.resume
import superapp.shared.generated.resources.start
import superapp.shared.generated.resources.stretching

@Composable
fun StretchingRoutineScreen(
    viewModel: StretchingRoutineViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when {
        uiState.showVoiceUnavailableDialog != null -> {
            VoiceUnavailableDialog(
                onDismiss = viewModel::onVoiceUnavailableDialogDismiss,
                onSettingsClick = viewModel::onVoiceUnavailableDialogSettingsClick,
            )
        }
        uiState.showVoiceDownloadSettings != null -> {
            // todo
//            val launcher = rememberLauncherForActivityResult(
//                ActivityResultContracts.StartActivityForResult()
//            ) {
//                viewModel.onVoiceDownloadSettingsResult()
//            }
//            LaunchedEffect(uiState.showVoiceDownloadSettings) {
//                viewModel.onVoiceDownloadSettingsShown()
//                //                ACTION_INSTALL_TTS_DATA
//                val installIntent = Intent().apply {
//                    setAction("com.android.settings.TTS_SETTINGS")
//                }
//                launcher.launch(installIntent)
//            }
        }
    }
    StretchingRoutineScreen(
        uiState = uiState,
        callbacks = StretchingRoutineCallbacks(
            onStartPauseClick = viewModel::onStartPauseClick,
            onLanguageClick = viewModel::onLanguageClick,
            onStopClick = viewModel::onStopClick,
            onEngineClick = viewModel::onEngineClick,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StretchingRoutineScreen(
    uiState: StretchingRoutineUiState,
    callbacks: StretchingRoutineCallbacks,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(Res.string.stretching))
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            val state = uiState.state
            val isNotIdle = state != StretchingRoutineState.IDLE
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row {
                    OutlinedButton(
                        onClick = callbacks.onStartPauseClick,
                    ) {
                        val startPauseTextRes = when (state) {
                            StretchingRoutineState.RUNNING -> {
                                Res.string.pause
                            }
                            StretchingRoutineState.PAUSED -> {
                                Res.string.resume
                            }
                            else -> {
                                Res.string.start
                            }
                        }
                        Text(text = stringResource(startPauseTextRes))
                    }
                    if (isNotIdle) {
                        OutlinedButton(
                            modifier = Modifier.padding(start = 8.dp),
                            onClick = callbacks.onStopClick,
                        ) {
                            Icon(painter = painterResource(Res.drawable.ic_stop), contentDescription = null)
                        }
                    }
                }

                if (isNotIdle) {
                    Row(
                        modifier = Modifier.padding(top = 16.dp),
                    ) {
                        Text(text = uiState.exercise.transformToStringCompose())
                        Text(" - ")
                        Text(text = uiState.step.transformToStringCompose())
                    }
                }

                val currentLocale = uiState.currentLocale
                val locales = uiState.supportedLocales
                if (currentLocale != null && locales.isNotEmpty()) {
                    LocaleDropdown(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        currentLocale = currentLocale,
                        locales = locales,
                        onLanguageClick = callbacks.onLanguageClick,
                    )
                }

                val currentEngine = uiState.currentTextToSpeechEngine
                val engines = uiState.textToSpeechEngines
                if (engines.count() > 1 && currentEngine != null) {
                    TextToSpeechEngineDropdown(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        currentEngine = currentEngine,
                        engines = engines,
                        onEngineClick = callbacks.onEngineClick,
                    )
                }
            }
        }
    }
}