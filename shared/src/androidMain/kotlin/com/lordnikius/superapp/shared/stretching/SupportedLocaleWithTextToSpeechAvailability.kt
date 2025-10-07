package com.lordnikius.superapp.shared.stretching

import com.lordnikius.superapp.shared.util.locale.SupportedLocale

data class SupportedLocaleWithTextToSpeechAvailability(
    val supportedLocale: SupportedLocale,
    val isTextToSpeechAvailable: Boolean,
)