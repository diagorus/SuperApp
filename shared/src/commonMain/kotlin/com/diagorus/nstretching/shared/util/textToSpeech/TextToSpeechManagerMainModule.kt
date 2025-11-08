package com.diagorus.nstretching.shared.util.textToSpeech

import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Module
@Configuration
expect class TextToSpeechManagerMainModule() {

    @Single
    fun provideTextToSpeechManager(scope: Scope): TextToSpeechManager
}