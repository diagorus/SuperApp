package com.lordnikius.superapp.util.locale

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.lordnikius.superapp.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocaleManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    val supportedLocales: List<SupportedLocale> by lazy {
        BuildConfig.SUPPORTED_LOCALE_TAGS.map {
            languageTagToSupportedLocale(it)
        }
    }

    private fun languageTagToSupportedLocale(languageTag: String): SupportedLocale {
        val locale = Locale.forLanguageTag(languageTag)
        return locale.toSupportedLocale()
    }

    fun Locale.toSupportedLocale(): SupportedLocale {
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