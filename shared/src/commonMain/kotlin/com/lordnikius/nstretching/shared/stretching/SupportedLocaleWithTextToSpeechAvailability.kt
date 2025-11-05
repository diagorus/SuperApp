package com.lordnikius.nstretching.shared.stretching

import com.lordnikius.nstretching.shared.util.locale.SupportedLocale

data class SupportedLocaleWithTextToSpeechAvailability(
    val supportedLocale: SupportedLocale,
    val isTextToSpeechAvailable: Boolean,
)