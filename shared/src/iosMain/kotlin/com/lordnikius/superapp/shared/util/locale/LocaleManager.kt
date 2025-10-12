package com.lordnikius.superapp.shared.util.locale

import org.koin.core.annotation.Single
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.Foundation.localeIdentifier

actual class LocaleManager {

    actual val supportedLocales: List<SupportedLocale> by lazy {
        emptyList()
    }

    actual fun getCurrentLocale(): SupportedLocale {
        return SupportedLocale(
            "",
            "",
        )
    }

    actual fun setLocale(locale: SupportedLocale) {
        // do nothing
    }
}