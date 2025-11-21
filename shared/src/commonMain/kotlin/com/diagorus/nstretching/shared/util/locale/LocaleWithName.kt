package com.diagorus.nstretching.shared.util.locale

import com.diagorus.nstretching.shared.generated.resources.Res
import com.diagorus.nstretching.shared.generated.resources.english

data class LocaleWithName(
    val supportedLocale: SupportedLocale,
    val displayName: StringUiData,
) {

    companion object Companion {
        val default = LocaleWithName(
            supportedLocale = SupportedLocale.default,
            displayName = StringUiData.Resource(Res.string.english),
        )
    }
}