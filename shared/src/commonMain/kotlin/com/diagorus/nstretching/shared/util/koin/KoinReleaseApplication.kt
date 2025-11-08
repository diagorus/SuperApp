package com.diagorus.nstretching.shared.util.koin

import org.koin.core.annotation.KoinApplication

@KoinApplication(configurations = [DEFAULT_CONFIGURATION, RELEASE_CONFIGURATION])
object KoinReleaseApplication