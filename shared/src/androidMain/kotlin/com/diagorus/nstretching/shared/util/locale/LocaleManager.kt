package com.diagorus.nstretching.shared.util.locale

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.diagorus.nstretching.shared.util.environment.customAppLocale
import java.util.Locale

actual class LocaleManager(
    private val context: Context,
) {

    actual val supportedLocales: List<LocaleWithName> by lazy {
        SupportedLocale.entries
            .map {
                languageTagToSupportedLocale(it.tag)
            }
    }

    private fun languageTagToSupportedLocale(languageTag: String): LocaleWithName {
        val locale = Locale.forLanguageTag(languageTag)
        return locale.toSupportedLocaleWithName()
    }

    private fun Locale.toSupportedLocaleWithName(): LocaleWithName {
        val displayName = getDisplayLanguage(this).capitalize(this)
        val displayNameUiData = StringUiData.Value(displayName)
        val supportedLocale = SupportedLocale.fromLanguageTag(language)
        return LocaleWithName(
            supportedLocale = supportedLocale,
            displayName = displayNameUiData,
        )
    }

    actual fun getCurrentLocale(): LocaleWithName {
        val chosenLocale = AppCompatDelegate.getApplicationLocales()
            .toLanguageTags()
            .split(',')
            .firstOrNull { it.isNotEmpty() }
            ?.let {
                languageTagToSupportedLocale(it)
            }
        return chosenLocale ?: getDefaultDeviceLocale()
    }

    private fun getDefaultDeviceLocale(): LocaleWithName {
        val defaultLocale = context.resources.configuration.locales[0]
        return defaultLocale.toSupportedLocaleWithName()
    }

    actual fun setLocale(locale: LocaleWithName) {
        customAppLocale = locale.supportedLocale.tag
    }

    fun onLocaleChanged() {
        customAppLocale = getCurrentLocale().supportedLocale.tag
    }
}