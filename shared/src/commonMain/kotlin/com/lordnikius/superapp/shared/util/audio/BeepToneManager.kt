package com.lordnikius.superapp.shared.util.audio

expect class BeepToneManager {
    suspend fun playBeep()
    suspend fun playDoubleBeep()
}