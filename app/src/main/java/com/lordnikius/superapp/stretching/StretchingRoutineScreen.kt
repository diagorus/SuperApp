package com.lordnikius.superapp.stretching

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lordnikius.superapp.util.StringUiData
import com.lordnikius.superapp.R

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

                //todo dimens from theme
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = uiState.exerciseAndStep.transformToString(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun StretchingRoutineScreenPreview0() {
    MaterialTheme {
        StretchingRoutineScreen(
            StretchingRoutineUiState(
                exerciseAndStep = StringUiData.Value("Hands - Preparation"),
            ),
            onStartClick = {},
        )
    }
}