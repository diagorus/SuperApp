package com.lordnikius.nstretching.shared.stretching.ui


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import com.lordnikius.nstretching.shared.generated.resources.Res
import com.lordnikius.nstretching.shared.generated.resources.ic_speaker_crossed
import com.lordnikius.nstretching.shared.generated.resources.to_settings
import com.lordnikius.nstretching.shared.generated.resources.voice_unavailable_description
import com.lordnikius.nstretching.shared.generated.resources.voice_unavailable_title

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
            Icon(painter = painterResource(Res.drawable.ic_speaker_crossed), contentDescription = null)
        },
        confirmButton = {
            TextButton(
                onClick = onSettingsClick,
            ) {
                Text(text = stringResource(Res.string.to_settings))
            }
        },
        text = {
            Text(text = stringResource(Res.string.voice_unavailable_description))
        },
        title = {
            Text(text = stringResource(Res.string.voice_unavailable_title))
        },
    )
}