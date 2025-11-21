package com.diagorus.nstretching.shared.stretching.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.diagorus.nstretching.shared.stretching.LocaleWithTextToSpeechAvailability
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import com.diagorus.nstretching.shared.generated.resources.Res
import com.diagorus.nstretching.shared.generated.resources.ic_speaker
import com.diagorus.nstretching.shared.generated.resources.language
import com.diagorus.nstretching.shared.util.locale.transformToStringCompose

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocaleDropdown(
    modifier: Modifier,
    currentLocale: LocaleWithTextToSpeechAvailability,
    locales: List<LocaleWithTextToSpeechAvailability>,
    onLanguageClick: (LocaleWithTextToSpeechAvailability) -> Unit,
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
                Text(stringResource(Res.string.language))
            },
            value = currentLocale.localeWithName.displayName.transformToStringCompose(),
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
                            painter = painterResource(Res.drawable.ic_speaker),
                            contentDescription = null,
                        )
                    }
                } else {
                    null
                }
                DropdownMenuItem(
                    text = {
                        Text(locale.localeWithName.displayName.transformToStringCompose())
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