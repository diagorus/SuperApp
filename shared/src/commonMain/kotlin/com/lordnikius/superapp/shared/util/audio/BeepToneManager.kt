package com.lordnikius.superapp.shared.util.audio

import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

expect class BeepToneManager {
    fun playSingleBeepTone()
    fun playDoubleBeepTone()
}