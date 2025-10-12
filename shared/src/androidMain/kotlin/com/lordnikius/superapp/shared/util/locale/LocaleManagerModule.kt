package com.lordnikius.superapp.shared.util.locale

import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

actual class LocaleManagerModule {

    @Single
    actual fun provideLocaleManager(scope: Scope) = LocaleManager(scope.get())
}