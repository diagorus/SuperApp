package com.lordnikius.superapp.util

import android.app.LocaleConfig
import android.app.LocaleManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.LocaleManagerCompat
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocaleManager @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
)  {

    fun getLocales(): List<String> {

        return LocaleManagerCompat.getApplicationLocales(applicationContext).toLanguageTags().split(',')
    }
}