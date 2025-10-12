package com.lordnikius.superapp.shared.util.locale

import org.koin.core.annotation.Single

expect class LocaleManager {

    val supportedLocales: List<SupportedLocale>

    fun getCurrentLocale(): SupportedLocale

    fun setLocale(locale: SupportedLocale)
}