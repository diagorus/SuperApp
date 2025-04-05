package com.lordnikius.superapp.util.locale

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject

class LocaleManager @Inject constructor() {

    fun getAvailableLocales(): List<String> {
        return Locale.getAvailableLocales().toList().map { it.getDisplayName(it) }
    }

    fun getLocale(): Locale {
        return AppCompatDelegate.getApplicationLocales()[0] ?: Locale.getDefault()
    }
}