package com.lordnikius.nstretching.shared.util.audio

import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Module
@Configuration
expect class BeepToneManagerModule() {

    @Single
    fun provideBeepToneManager(scope: Scope): BeepToneManager
}