package com.lordnikius.superapp.shared.util.textToSpeech

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope

@Module
@Configuration
@ComponentScan
actual class TextToSpeechManagerMainModule {

    @Single
    actual fun provideTextToSpeechManager(scope: Scope): TextToSpeechManager {
        return TextToSpeechManager()
    }
}