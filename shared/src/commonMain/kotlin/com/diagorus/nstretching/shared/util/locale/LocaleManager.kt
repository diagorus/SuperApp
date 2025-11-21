package com.diagorus.nstretching.shared.util.locale

expect class LocaleManager {

    val supportedLocales: List<LocaleWithName>

    fun getCurrentLocale(): LocaleWithName

    fun setLocale(locale: LocaleWithName)
}