package com.lordnikius.superapp.stretching.ui

import android.content.Intent
import android.content.res.Resources
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import com.lordnikius.superapp.R
import com.lordnikius.superapp.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.lordnikius.superapp.stretching.viewModel.ShowVoiceUnavailableDialog
import com.lordnikius.superapp.stretching.viewModel.StretchingRoutineState
import com.lordnikius.superapp.stretching.viewModel.StretchingRoutineUiState
import com.lordnikius.superapp.stretching.viewModel.StretchingRoutineViewModel
import com.lordnikius.superapp.theme.AppTheme
import com.lordnikius.superapp.util.NO_VALUE
import com.lordnikius.superapp.data.util.locale.StringUiData
import com.lordnikius.superapp.util.locale.SupportedLocale
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechEngine

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
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                viewModel.onVoiceDownloadSettingsResult()
            }
            LaunchedEffect(uiState.showVoiceDownloadSettings) {
                viewModel.onVoiceDownloadSettingsShown()
                //                ACTION_INSTALL_TTS_DATA
                val installIntent = Intent().apply {
                    setAction("com.android.settings.TTS_SETTINGS")
                }
                launcher.launch(installIntent)
            }
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
                    Text(text = stringResource(id = R.string.stretching))
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
                                R.string.pause
                            }
                            StretchingRoutineState.PAUSED -> {
                                R.string.resume
                            }
                            else -> {
                                R.string.start
                            }
                        }
                        Text(text = stringResource(id = startPauseTextRes))
                    }
                    if (isNotIdle) {
                        OutlinedButton(
                            modifier = Modifier.padding(start = 8.dp),
                            onClick = callbacks.onStopClick,
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ic_stop), contentDescription = null)
                        }
                    }
                }

                if (isNotIdle) {
                    Row(
                        modifier = Modifier.padding(top = 16.dp),
                    ) {
                        Text(text = uiState.exercise.transformToString())
                        Text(" - ")
                        Text(text = uiState.step.transformToString())
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocaleDropdown(
    modifier: Modifier = Modifier,
    currentLocale: SupportedLocaleWithTextToSpeechAvailability,
    locales: List<SupportedLocaleWithTextToSpeechAvailability>,
    onLanguageClick: (SupportedLocaleWithTextToSpeechAvailability) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
            readOnly = true,
            label = {
                Text(stringResource(id = R.string.language))
            },
            value = currentLocale.supportedLocale.displayName,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            locales.forEach { locale ->
                val trailingIcon = if (locale.isTextToSpeechAvailable) {
                    @Composable {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_speaker),
                            contentDescription = null,
                        )
                    }
                } else {
                    null
                }
                DropdownMenuItem(
                    text = {
                        Text(locale.supportedLocale.displayName)
                    },
                    trailingIcon = trailingIcon,
                    onClick = {
                        expanded = false
                        onLanguageClick(locale)
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextToSpeechEngineDropdown(
    modifier: Modifier = Modifier,
    currentEngine: TextToSpeechEngine,
    engines: List<TextToSpeechEngine>,
    onEngineClick: (TextToSpeechEngine) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
            readOnly = true,
            label = {
                Text(stringResource(id = R.string.text_to_speech_engine))
            },
            value = currentEngine.label,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            engines.forEach { engine ->
                DropdownMenuItem(
                    text = {
                        Text(engine.label)
                    },
                    onClick = {
                        expanded = false
                        onEngineClick(engine)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun StretchingRoutineScreenPreview0() {
    AppTheme {
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