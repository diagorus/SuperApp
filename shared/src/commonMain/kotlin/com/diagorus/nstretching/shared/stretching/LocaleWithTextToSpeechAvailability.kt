package com.diagorus.nstretching.shared.stretching

import com.diagorus.nstretching.shared.util.locale.LocaleWithName

data class LocaleWithTextToSpeechAvailability(
    val localeWithName: LocaleWithName,
    val isTextToSpeechAvailable: Boolean,
)