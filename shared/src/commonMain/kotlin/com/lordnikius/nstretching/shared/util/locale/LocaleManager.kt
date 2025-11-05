package com.lordnikius.nstretching.shared.util.locale

expect class LocaleManager {

    val supportedLocales: List<SupportedLocale>

    fun getCurrentLocale(): SupportedLocale

    fun setLocale(locale: SupportedLocale)
}