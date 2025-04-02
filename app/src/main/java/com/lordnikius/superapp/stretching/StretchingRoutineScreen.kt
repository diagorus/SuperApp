package com.lordnikius.superapp.stretching

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lordnikius.superapp.util.StringUiData
import com.lordnikius.superapp.R
import com.lordnikius.superapp.theme.AppTheme

@Composable
fun StretchingRoutineScreen(viewModel: StretchingRoutineViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StretchingRoutineScreen(
        uiState = uiState,
        onStartClick = viewModel::onStartClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StretchingRoutineScreen(
    uiState: StretchingRoutineUiState,
    onStartClick: () -> Unit,
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
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedButton(
                    onClick = onStartClick,
                ) {
                    Text(text = stringResource(id = R.string.start))
                }

                Row(
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    Text(text = uiState.exercise.transformToString())
                    Text(" - ")
                    Text(text = uiState.step.transformToString())
                }

                LocaleDropdownMenu()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocaleDropdownMenu() {
    val localeOptions = mapOf(
        stringResource(R.string.english) to "en",
        stringResource(R.string.german) to "de",
        stringResource(R.string.russian) to "ru",
        stringResource(R.string.ukrainian) to "uk"
    )

    val currentLocale = AppCompatDelegate.getApplicationLocales().toLanguageTags()

    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
            readOnly = true,
            value = currentLocale,
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
            localeOptions.keys.forEach { selectionLocale ->
                DropdownMenuItem(
                    text = {
                        Text(selectionLocale)
                    },
                    onClick = {
                        expanded = false
                        AppCompatDelegate.setApplicationLocales(
                            LocaleListCompat.forLanguageTags(
                                localeOptions[selectionLocale]
                            )
                        )
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
                exercise = StringUiData.Value("Hands"),
                step = StringUiData.Value("Preparation"),
            ),
            onStartClick = {},
        )
    }
}