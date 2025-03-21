package com.lordnikius.superapp.util.module.coroutines

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutinesModule {

    @ApplicationScope
    @Singleton
    @Provides
    fun provideApplicationScope(
        @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher,
        coroutineExceptionHandler: CoroutineExceptionHandler,
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + coroutineDispatcher + coroutineExceptionHandler)
    }

    @Singleton
    @Provides
    fun provideCoroutineExceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception -> Timber.e(exception.toString()) }
    }

    @IoDispatcher
    @Singleton
    @Provides
    fun provideIoCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @DefaultDispatcher
    @Singleton
    @Provides
    fun provideComputeIntensiveCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}
