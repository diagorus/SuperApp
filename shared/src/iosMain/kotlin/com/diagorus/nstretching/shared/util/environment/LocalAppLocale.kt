package com.diagorus.nstretching.shared.util.environment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.preferredLanguages

actual object LocalAppLocale {

    actual val current: String
        @Composable get() = LocalAppLocale.current

    private val currentLanguage = NSLocale.preferredLanguages.first() as String
    private val LocalAppLocale = staticCompositionLocalOf { currentLanguage }

    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        val new = value ?: currentLanguage
        if (value == null) {
            NSUserDefaults.standardUserDefaults.removeObjectForKey(KEY_APPLE_LANGUAGES)
        } else {
            NSUserDefaults.standardUserDefaults.setObject(arrayListOf(new), KEY_APPLE_LANGUAGES)
            NSUserDefaults.standardUserDefaults.synchronize()
        }
        return LocalAppLocale.provides(new)
    }

    private const val KEY_APPLE_LANGUAGES = "AppleLanguages"
}