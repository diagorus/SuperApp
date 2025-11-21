package com.diagorus.nstretching.shared.util.locale

import com.diagorus.nstretching.shared.generated.resources.Res
import com.diagorus.nstretching.shared.generated.resources.english

enum class SupportedLocale(
    val tag: String,
) {
    GERMAN("de"),
    ENGLISH("en"),
    UKRAINIAN("uk"),
    RUSSIAN("ru");

    companion object Companion {

        val default = ENGLISH

        fun fromLanguageTag(languageTag: String?): SupportedLocale {
            Res.string.english
            return entries.find { it.tag == languageTag } ?: default
        }
    }
}