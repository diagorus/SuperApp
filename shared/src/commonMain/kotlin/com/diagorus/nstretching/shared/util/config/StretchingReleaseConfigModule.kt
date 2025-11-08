package com.diagorus.nstretching.shared.util.config

import com.diagorus.nstretching.shared.util.koin.RELEASE_CONFIGURATION
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@Configuration(RELEASE_CONFIGURATION)
class StretchingReleaseConfigModule {

    @Factory
    fun provideReleaseStretchingConfig(): StretchingConfig = StretchingReleaseConfig()
}