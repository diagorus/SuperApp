package com.lordnikius.superapp.stretching

import com.lordnikius.superapp.util.locale.SupportedLocale

data class SupportedLocaleWithTextToSpeechAvailability(
    val supportedLocale: SupportedLocale,
    val isTextToSpeechAvailable: Boolean,
)