package com.lordnikius.nstretching.shared.util.config

import com.lordnikius.nstretching.shared.util.koin.DEBUG_CONFIGURATION
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@Configuration(DEBUG_CONFIGURATION)
class StretchingDebugConfigModule {

    @Factory
    fun provideStretchingDebugConfig(): StretchingConfig = StretchingDebugConfig()
}