package com.lordnikius.superapp.shared.util.koin

import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.startKoin

fun initKoin(config: KoinAppDeclaration? = null) {
//    val koinInitializer = if (isDebug()) {
//        KoinDebugApplication::startKoin
//    } else {
//        KoinReleaseApplication::startKoin
//    }
    KoinDebugApplication.startKoin(config)
}