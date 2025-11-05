package com.lordnikius.nstretching.shared.util.locale

import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Module
@Configuration
expect class LocaleManagerModule() {

    @Single
    fun provideLocaleManager(scope: Scope): LocaleManager
}