package com.lordnikius.superapp.shared.util.locale

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import org.koin.core.annotation.Single
import java.util.Locale

@Single
class LocaleManager(
    private val context: Context,
) {

    val supportedLocales: List<SupportedLocale> by lazy {
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

    fun getCurrentLocale(): SupportedLocale {
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

    fun setLocale(locale: SupportedLocale) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(locale.languageTag)
        )
    }
}