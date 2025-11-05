package com.lordnikius.nstretching.shared.util.koin

import org.koin.core.annotation.KoinApplication

@KoinApplication(configurations = [DEFAULT_CONFIGURATION, DEBUG_CONFIGURATION])
object KoinDebugApplication