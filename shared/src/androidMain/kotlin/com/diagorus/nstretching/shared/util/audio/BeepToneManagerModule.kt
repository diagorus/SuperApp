package com.diagorus.nstretching.shared.util.audio

import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.qualifier.StringQualifier
import org.koin.core.scope.Scope

@Module
@Configuration
actual class BeepToneManagerModule {

    @Single
    actual fun provideBeepToneManager(scope: Scope): BeepToneManager {
        // generated classes transform named annotations into StringQualifier; drawback of dynamic injection
        return BeepToneManager(
            scope.get(StringQualifier("com.diagorus.nstretching.shared.util.koin.CoroutinesModule.DefaultDispatcher"))
        )
    }
}