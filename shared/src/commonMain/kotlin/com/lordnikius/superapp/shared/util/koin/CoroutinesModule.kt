package com.lordnikius.superapp.shared.util.koin

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named

@Module
@Configuration
class CoroutinesModule {

    @Factory
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Factory
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Factory
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Factory
    fun provideCoroutineExceptionHandler() = CoroutineExceptionHandler { _, exception ->
        Logger.e(exception) { "In injected CoroutineExceptionHandler" }
    }

    @Factory
    @ApplicationScope
    fun provideApplicationScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
        coroutineExceptionHandler: CoroutineExceptionHandler
    ) =
        CoroutineScope(SupervisorJob() + defaultDispatcher + coroutineExceptionHandler)

    @Named
    annotation class IoDispatcher

    @Named
    annotation class MainDispatcher

    @Named
    annotation class DefaultDispatcher

    @Named
    annotation class ApplicationScope
}