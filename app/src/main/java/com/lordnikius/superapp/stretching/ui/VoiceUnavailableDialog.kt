package com.lordnikius.superapp.stretching.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lordnikius.superapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoiceUnavailableDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        icon = {
            Icon(painter = painterResource(id = R.drawable.ic_speaker_crossed), contentDescription = null)
        },
        confirmButton = {
            TextButton(
                onClick = onSettingsClick,
            ) {
                Text(text = stringResource(id = R.string.to_settings))
            }
        },
        text = {
            Text(text = stringResource(id = R.string.voice_unavailable_description))
        },
        title = {
            Text(text = stringResource(id = R.string.voice_unavailable_title))
        },
    )
}