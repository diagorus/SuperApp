package com.diagorus.nstretching.shared.util.locale

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

actual class LocaleManager(
    private val context: Context,
) {

    actual val supportedLocales: List<SupportedLocale> by lazy {
        listOf(
            "en",
            "de",
            "uk",
            "ru",
        )
            .map {
                languageTagToSupportedLocale(it)
            }
    }

    private fun languageTagToSupportedLocale(languageTag: String): SupportedLocale {
        val locale = Locale.forLanguageTag(languageTag)
        return locale.toSupportedLocale()
    }

    private fun Locale.toSupportedLocale(): SupportedLocale {
        val displayName = getDisplayLanguage(this).capitalize(this)
        return SupportedLocale(
            languageTag = language,
            displayName = displayName,
        )
    }

    actual fun getCurrentLocale(): SupportedLocale {
        val chosenLocale = AppCompatDelegate.getApplicationLocales()
            .toLanguageTags()
            .split(',')
            .firstOrNull { it.isNotEmpty() }
            ?.let {
                languageTagToSupportedLocale(it)
            }
        return chosenLocale ?: getDefaultDeviceLocale()
    }

    private fun getDefaultDeviceLocale(): SupportedLocale {
        val defaultLocale = context.resources.configuration.locales[0]
        return defaultLocale.toSupportedLocale()
    }

    actual fun setLocale(locale: SupportedLocale) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(locale.languageTag)
        )
    }
}