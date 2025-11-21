package com.diagorus.nstretching.shared.util.environment

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.LocaleListCompat

actual object LocalAppLocale {

    actual val current: String
        @Composable get() = LocalAppLocale.current

    private val defaultLanguage = Locale.current.language
    private val LocalAppLocale = staticCompositionLocalOf { defaultLanguage }

    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        val new = value ?: defaultLanguage

        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(new)
        )

        return LocalAppLocale.provides(value ?: defaultLanguage)
    }
}