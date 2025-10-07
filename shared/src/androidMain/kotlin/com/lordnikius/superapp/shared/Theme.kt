package com.lordnikius.superapp.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun App(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        content = content
    )
}

