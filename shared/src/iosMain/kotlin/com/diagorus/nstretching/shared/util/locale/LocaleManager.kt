package com.diagorus.nstretching.shared.util.locale

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