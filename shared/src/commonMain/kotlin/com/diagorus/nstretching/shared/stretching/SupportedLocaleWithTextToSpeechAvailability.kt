package com.diagorus.nstretching.shared.stretching

import com.diagorus.nstretching.shared.util.locale.SupportedLocale

data class SupportedLocaleWithTextToSpeechAvailability(
    val supportedLocale: SupportedLocale,
    val isTextToSpeechAvailable: Boolean,
)