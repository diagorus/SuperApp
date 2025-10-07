package com.lordnikius.superapp.shared.util.config

import com.lordnikius.superapp.shared.util.koin.DEBUG_CONFIGURATION
import com.lordnikius.superapp.shared.util.koin.DEFAULT_CONFIGURATION
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@Configuration(DEBUG_CONFIGURATION)
class StretchingDebugConfigModule {

    @Factory
    fun provideStretchingDebugConfig(): StretchingConfig = StretchingDebugConfig()
}